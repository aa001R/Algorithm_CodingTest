import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int vertex, weight;
		Node next;
		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	static class Vertex implements Comparable<Vertex>{
		int no, totalDistance;
		public Vertex(int no, int totalDistance) {
			this.no = no;
			this.totalDistance = totalDistance;
		}
		@Override
		public int compareTo(Vertex o) {
			return this.totalDistance - o.totalDistance;
		}
	}
	static int INF = Integer.MAX_VALUE;
	static int V, E, v1, v2;
	static Node[] adjList;
	static int [] distance;
	static boolean [] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken()); // 정점 갯수
		int E = Integer.parseInt(st.nextToken()); // 간선 갯수
		adjList = new Node[V + 1];
		visited = new boolean[V+1];
		distance = new int[V+1];
		for(int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
			adjList[to] = new Node(from, weight, adjList[to]);
		}
		st = new StringTokenizer(br.readLine(), " ");
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		int startToV1 = dijkstra(1, v1);
		int startToV2 = dijkstra(1, v2);
		int v1ToV2 = dijkstra(v1, v2);
		int v1ToEnd = dijkstra(v1, V);
		int v2ToEnd = dijkstra(v2, V);
		int startV1End = startToV1+v1ToV2+v2ToEnd;
		int startV2End = startToV2+v1ToV2+v1ToEnd;
		int distance = Math.min(startV1End, startV2End);
		if(distance <= 0) distance = -1;
		System.out.println(distance);
	}
	static int dijkstra(int start, int end) {
		Arrays.fill(distance, INF);
		Arrays.fill(visited, false);
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		distance[start] = 0;
		pq.offer(new Vertex(start, distance[start]));
		Vertex cur = null;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			if(cur.no == end) return cur.totalDistance;
			if(visited[cur.no]) continue; visited[cur.no] = true;
			for(Node tmp = adjList[cur.no]; tmp != null; tmp = tmp.next) {
				if(visited[tmp.vertex]) continue;
				if(distance[tmp.vertex] <= cur.totalDistance + tmp.weight ) continue;
				distance[tmp.vertex] = cur.totalDistance + tmp.weight;
				pq.offer(new Vertex(tmp.vertex, distance[tmp.vertex]));
			}
		}
		return distance[end] != INF ? distance[end] : -200_000 * 1_000_0 ;
	}
}
