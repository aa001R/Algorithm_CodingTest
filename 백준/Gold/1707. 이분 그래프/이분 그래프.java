import java.util.*;
import java.io.*;

class Main {
	static class Node{
		int num;
		Node next;
		public Node(int num, Main.Node next) {
			this.num = num;
			this.next = next;
		}
	}
	static final int RED = 1,  BLUE = -1;
    static int[] colors; // 색 {RED 1 or BLUE -1}
    static boolean isBinaryGraph; // 이분 그래프인지 아닌지 확인
    static int V, E;
    static Node[] graph; // 그래프
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // 정점의 개수 V (1 <= V 2<= 20_000)
			E = Integer.parseInt(st.nextToken()); // 간선의 개수 E (1 <= E <= 200_000)
			colors = new int[V+1]; // 각 정점의 색을 구분
			graph = new Node[V+1];
			isBinaryGraph = true;
			for(int i = 0; i < E; i++) { // 양방향 그래프
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				graph[n1] = new Node(n2, graph[n1]);
				graph[n2] = new Node(n1, graph[n2]);
			}
			// 이분 그래프: 같은 레벨의 꼭짓점끼리는 무조건 같은 색, 인접한 정점 사이는 다른 색
			// 주의! 연결 그래프와 비연결 그래프(모든 정점을 돌면서 확인) 모두 고려!!
			// 문제에서는 연결 그래프란 보장 없음
			for (int i = 1; i < V + 1; i++) {
                // 이분 그래프가 아니면 반복 탈출
                if (!isBinaryGraph) break;
                // 방문하지 않은 정점에 대해서 탐색 수행
                if (colors[i] != 0) continue;
                //dfs(i, RED); /* 깊이 우선 탐색 */
                bfs(i, RED); /* 너비 우선 탐색 */
            }
			bw.write(isBinaryGraph ? "YES\n" : "NO\n");
		}
		bw.flush();
	}
	
	// 깊이 우선 탐색
	static void dfs(int startV, int color) {
        colors[startV] = color; // 시작 정점의 색을 설정
        for(Node tmp = graph[startV]; tmp != null; tmp = tmp.next) {
            // 시작 정점의 색과 인접한 정점의 색이 같으면 이분 그래프가 아니다.
            if (colors[tmp.num] == color) {
                isBinaryGraph = false;
                return;
            } // 시작 정점과 인접한 정점이 방문하지 않은 정점이면 dfs 실행
            if (colors[tmp.num] == 0) {
                // 인접한 정점을 다른 색으로 지정
                dfs(tmp.num, -color);
            }
        }

    }
	
	// 너비 우선 탐색
	private static void bfs(int startV, int color) {
		ArrayDeque<Integer> que = new ArrayDeque<>();
		que.offer(startV);
		colors[startV] = color; // 방문 & 색깔 표시
		while(!que.isEmpty()) {
			int v = que.poll();
			// 해당 정점과 연결된 모든 인접 정점을 방문
			for(Node tmp = graph[v]; tmp != null; tmp = tmp.next) {
				// 다른 색 정점 방문시 넘어가기
				if(colors[tmp.num] == colors[v] * -1) continue;
				// 서로 인접한 정점의 색이 같은 색이면 이분 그래프가 아니다.
                if (colors[v] == colors[tmp.num]) {
                    isBinaryGraph = false;
                    return;
                }
				// 방문하지 않은 정점이면 
				if(colors[tmp.num] == 0) {
					colors[tmp.num] = colors[v] * -1;
					que.offer(tmp.num);
				} 
			}
		}
	}
}