import java.util.*;
import java.io.*;

class Main {
	static int N, max = 1;
	static char[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		// 행을 기준으로 오른쪽 색과 변경
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N - 1; c++) {
				swap(r, c, r, c + 1);
				search();
				swap(r, c + 1, r, c);
			}
		}
		// 열을 기준으로 아래쪽 색과 변경
		for (int r = 0; r < N - 1; r++) {
			for (int c = 0; c < N; c++) {
				swap(r, c, r + 1, c);
				search();
				swap(r + 1, c, r, c);
			}
		}
		System.out.println(max);
	}

	private static void search() {
		// 행에서 긴 수열 탐색
		for(int r = 0; r < N; r++) {
			int len = 1;
			for(int c = 0; c < N-1; c++) {
				if(board[r][c] == board[r][c+1]) {
					len++;
					max = Math.max(len, max);
				}else {
					len = 1;
				}
			}
		}
		// 열에서 긴 수열 탐색
		for(int c = 0; c < N; c++) {
			int len = 1;
			for(int r = 0; r < N-1; r++) {
				if(board[r][c] == board[r+1][c]) {
					len++;
					max = Math.max(len, max);
				}else {
					len = 1;
				}
			}
		}
	}

	private static void swap(int r1, int c1, int r2, int c2) {
		char tmp = board[r1][c1];
		board[r1][c1] = board[r2][c2];
		board[r2][c2] = tmp;
	}
}