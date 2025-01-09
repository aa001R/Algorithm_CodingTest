import java.util.*;
import java.io.*;

public class Main {
		static int R, C;
		static int max;
		static int[][] board;
		static int[] alpha;
		public static void main(String[] args) throws Exception {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			board = new int[R][C];
			alpha = new int[26];
			for (int i = 0; i < R; i++) {
				String input = br.readLine();
				for (int j = 0; j < C; j++) {
					board[i][j] = input.charAt(j) - 'A';
				}
			}
			max = 1;
			dfs(0, 0, 1);
			bw.append(Integer.toString(max));
			bw.flush();
		}
		
		static int [][]delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		static void dfs(int r, int c, int cnt) {
			alpha[board[r][c]]++;
			if(alpha[board[r][c]] > 1) {
				max = Math.max(max, cnt-1);
				return;
			}
			for(int d = 0; d < 4; d++) {
				int nr = delta[d][0] + r;
				int nc = delta[d][1] + c;
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				dfs(nr, nc, cnt + 1);
				alpha[board[nr][nc]]--;
			}
		}
	}
