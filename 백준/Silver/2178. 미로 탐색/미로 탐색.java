import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static String[] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new String[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			map[i] = st.nextToken();
		}
		System.out.println(bfs(0, 0));
	}

	static int bfs(int r, int c) {
		Queue<int[]> que = new ArrayDeque<>();
		int[][] visit = new int[N][M];
		int[][] delta = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

		que.offer(new int[] {r, c});
		visit[r][c] = 1;

		while (!que.isEmpty()) {
			int[] n = que.poll();
			for (int i = 0; i < 4; i++) {
				int nextr = n[0] + delta[i][0];
				int nextc = n[1] + delta[i][1];
				if (isIn(nextr, nextc) && visit[nextr][nextc] == 0 && map[nextr].charAt(nextc) == '1') {
					visit[nextr][nextc] = visit[n[0]][n[1]] + 1;
					que.offer(new int[] {nextr, nextc});
				}
			}
		}

		return visit[N - 1][M - 1];
	}

	static boolean isIn(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < M);
	}
}
