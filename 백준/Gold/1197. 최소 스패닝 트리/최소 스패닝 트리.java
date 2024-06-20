import java.util.*;
import java.io.*;

class Main {
	static class Edge implements Comparable<Edge> {
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static Edge[] edgeList;
	static int[] parents;
	static int V, E;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parents = new int[V+1];
		edgeList = new Edge[E];
		for (int i = 0; i < E; ++i) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		} // 간선 저장
		make();
		Arrays.sort(edgeList);
		int result = 0;
		int count = 0;// 연결 간선수
		for (Edge edge : edgeList) {
			if (!union(edge.start, edge.end)) continue;
			result += edge.weight;
			if (++count == V - 1) break;
		}
		System.out.println(result);
	}

	public static void make() {
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}

	public static int find(int a) {
		if (parents[a] == a) return a;// 자신이 루트이면 그냥 자신의 번호 리턴
		return parents[a] = find(parents[a]);
	}

	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		parents[bRoot] = aRoot;
		return true;
	}
}