import java.util.*;
import java.io.*;

class Main {
	static int L, W, maxHour;
	static char land[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		land = new char[L][W];
		for(int r = 0; r < L; r++) {
			String input = br.readLine();
			for(int c = 0; c < W; c++) {
				land[r][c] = input.charAt(c);
			}
		}
		maxHour = 0;
		for(int r = 0; r < L; r++) {
			for(int c = 0; c < W; c++) {
				if(land[r][c] == 'W') continue;
				bfs(r, c);
			}
		}
		bw.write(Integer.toString(maxHour));
		bw.flush();
	}
	private static void bfs(int sr, int sc) {
		ArrayDeque<int []> que = new ArrayDeque<>();
		boolean visited[][] = new boolean[L][W];
		int [][] delta = {{1, 0},{0, 1},{-1, 0},{0, -1}};
		que.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		int hour = -1;
		while(!que.isEmpty()) {
			hour++;
			for(int size = que.size(); size > 0; size--) {
				int[] cur = que.poll();
				for(int d = 0; d < 4; d++) {
					int nr = cur[0] + delta[d][0];
					int nc = cur[1] + delta[d][1];
					if(isOut(nr, nc)) continue;
					if (visited[nr][nc]) continue;
					visited[nr][nc] = true;
					if(land[nr][nc] == 'W') continue;
					que.offer(new int[] {nr, nc});
				}
			}
		}
		maxHour = Math.max(maxHour, hour);
		return;
	}

	private static boolean isOut(int nr, int nc) {
		return nr < 0 || nr >= L || nc < 0 || nc >= W;
	}
}