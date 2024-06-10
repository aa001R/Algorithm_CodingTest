import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*

최단거리

그래프 BFS
- 가중치가 없음
- 출발지와 도착지 각각 하나 존재

다익스트라
- 양의 가중치만
- 출발지와 도착지 각각 하나 존재
- 유/무향 그래프 가능

플로이드 워샬
- 양/음의 가중치
- 출발지와 도착지는 모든 정점
- 유/무향 그래프 가능

 */
public class Solution {

	private static final int BLANK = 0;  // 바다
	private static final int ISLAND = 1;  // 섬
	private static final int WHIRLPOOL = 2;  // 소용돌이

	// 우, 하, 좌, 상
	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { 1, 0, -1, 0 };

	private static class Node {
		public int x;
		public int y;
		public int time;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int time) {
			this(x, y);
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", time=" + time + "]";
		}
	}

	private static int N;  // 수영장의 크기
	private static int[][] map;  // 수영장의 모양
	private static Node end;  // 도착점

	// 결과를 한 번에 출력하기 위한 StringBuilder
	private static StringBuilder sb = new StringBuilder();

	public static void main(String args[]) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		/**
		 * 1. 입력 파일 객체화
		 */
		int T;
		T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			// 수영장의 크기
			N = Integer.parseInt(in.readLine());

			// 수영장의 모양
			map = new int[N][N];
			for (int x = 0; x < N; x++) {
				String[] split = in.readLine().split(" ");
				for (int y = 0; y < N; y++) {
					map[x][y] = Integer.parseInt(split[y]);
				}
			}

			// 시작점
			String[] split = in.readLine().split(" ");
			Node start = new Node(Integer.parseInt(split[0]), Integer.parseInt(split[1]));

			// 도착점
			split = in.readLine().split(" ");
			end = new Node(Integer.parseInt(split[0]), Integer.parseInt(split[1]));

			/**
			 * 2. 알고리즘 풀기
			 */
			int result = bfs(start);

			/**
			 * 3. 정답 출력
			 */
			sb.append(result).append("\n");
		}

		System.out.println(sb);
	}

	private static int bfs(Node start) {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];

		queue.offer(start);
		visited[start.x][start.y] = true;

		while (!queue.isEmpty()) {

			// 현재 정점 꺼내기
			Node curNode = queue.poll();
			
			// 기저부분 (도착점에 도착했다면)
			if (curNode.x == end.x && curNode.y == end.y) {
				return curNode.time;
			}

			// 4방향 탐색
			for (int i = 0; i < 4; i++) {
				int testX = curNode.x + dx[i];
				int testY = curNode.y + dy[i];

				// 경계 체크와 방문여부 확인
				if ((0 <= testX && testX < N) && (0 <= testY && testY < N)
						&& !visited[testX][testY]) {

					// 빈 칸이면 이동
					if (map[testX][testY] == BLANK) {
						queue.offer(new Node(testX, testY, curNode.time + 1));  // 다음 칸 이동
						visited[testX][testY] = true;  // 다음 칸 방문체크
					}
					// 소용돌이면
					else if (map[testX][testY] == WHIRLPOOL) {
						// 소용돌이는 0초, 1초까지 유지
						if (curNode.time % 3 == 0 || curNode.time % 3 == 1) {
							queue.offer(new Node(curNode.x, curNode.y, curNode.time + 1));  // 지나갈 수 없으므로 현재위치 머물기
							visited[curNode.x][curNode.y] = true;  // 현재위치 방문체크
						}
						// 소용돌이는 2초에 사라짐
						else {
							queue.offer(new Node(testX, testY, curNode.time + 1));  // 다음 칸 이동
							visited[testX][testY] = true;  // 다음 칸 방문체크
						}
					}
				}
			}
		}
		
		// 도착할 수 없다면
		return -1;
	}
}
