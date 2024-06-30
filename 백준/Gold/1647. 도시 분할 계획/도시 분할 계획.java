import java.util.*;
import java.io.*;

class Main {
	static class Edge implements Comparable<Edge>{
		int start, end, weight;
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int houseCnt, roadCnt, parents[];
	static ArrayList<Edge> edgeList;
	public static void main(String[] args) throws IOException
	{
		StringTokenizer st = new StringTokenizer(br.readLine());
		houseCnt = Integer.parseInt(st.nextToken());
		roadCnt = Integer.parseInt(st.nextToken());
		parents = new int[houseCnt+1];
		edgeList = new ArrayList<>();
		for (int i = 0; i < roadCnt; ++i) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(from, to, weight));
		}
		make();
		Collections.sort(edgeList);
		int priceSum = 0;
		int newRoadCnt = 0;
		for(Edge edge : edgeList) {
			if(!union(edge.start, edge.end)) continue;
			if(++newRoadCnt == houseCnt - 1) break;
			priceSum += edge.weight;
		}
		bw.append(Integer.toString(priceSum));
		bw.flush();
	}
	public static void make() {
		for (int i = 1; i <= houseCnt; i++) {
			parents[i] = i;
		}
	}
	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
}