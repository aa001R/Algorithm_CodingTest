import java.util.*;

class Solution {
    class Edge implements Comparable<Edge> {
        int start, end, weight;
        public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    static Edge[] edgeList;
	static int[] parents;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parents = new int[n];
        edgeList = new Edge[costs.length];
        for (int i = 0; i < costs.length; i++) {
            edgeList[i] = new Edge(costs[i][0], costs[i][1], costs[i][2]);
        }
        make(n);
		Arrays.sort(edgeList);
        
        int result = 0;
		int count = 0;// 연결 간선수
		for (Edge edge : edgeList) {
			if (!union(edge.start, edge.end)) continue;
			result += edge.weight;
			if (++count == n - 1) break;
		}
        return result;
    }
    
    public static void make(int n) {
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}

	public static int find(int a) {
		if (parents[a] == a) return a;// 자신이 루트이면 그냥 자신의 번호 리턴
		return parents[a] = find(parents[a]);
	}

	public static boolean union(int a, int b) {
		int aRoot = find(a), bRoot = find(b);
		if (aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
}