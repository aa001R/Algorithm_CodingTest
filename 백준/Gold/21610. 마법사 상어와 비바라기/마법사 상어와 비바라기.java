import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		int[][] cmd = new int[M][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			cmd[i][0] = Integer.parseInt(st.nextToken());
			cmd[i][1] = Integer.parseInt(st.nextToken());
		}

		int[][] delta = { { 0, 0 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 },
				{ 1, -1 } };
		Queue<int[]> que = new ArrayDeque<>();
		que.offer(new int[] { N - 1, 0 });
		que.offer(new int[] { N - 1, 1 });
		que.offer(new int[] { N - 2, 0 });
		que.offer(new int[] { N - 2, 1 });
		for (int m = 0; m < M; m++) {
			boolean[][] visited = new boolean[N][N];
			int size = que.size();
			for (int l = 0; l < size; l++) {
				int r = (que.peek()[0] + delta[cmd[m][0]][0] * cmd[m][1]) % N;
				int c = (que.poll()[1] + delta[cmd[m][0]][1] * cmd[m][1]) % N;
				r = r < 0 ? N + r : r;
				c = c < 0 ? N + c : c;
				// 1 증가
				map[r][c]++;
				// 구름 사라짐
				visited[r][c] = true;
				que.offer(new int[] { r, c });
			}
			// 대각선 탐색
			while (!que.isEmpty()) {
				int cnt = 0;
				int r = que.peek()[0];
				int c = que.poll()[1];
				for (int d = 2; d <= 8; d += 2) {
					int currR = r + delta[d][0];
					int currC = c + delta[d][1];
					if (currR >= 0 && currR < N && currC >= 0 && currC < N && map[currR][currC] > 0) {
						cnt++;
					}
				}
				map[r][c] += cnt;
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c] && map[r][c] >= 2) {
						que.offer(new int[] { r, c });
						map[r][c] -= 2;
					}
				}
			}
			
		}
		int sum = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				sum += map[r][c];
			}
		}
		bw.write(Integer.toString(sum));
		bw.flush();
	}
}
