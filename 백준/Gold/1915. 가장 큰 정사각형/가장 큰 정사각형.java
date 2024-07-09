import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char [][] board = new char[n+1][m+1];
		int [][] dp = new int[n+1][m+1];
		for(int i = 1; i <= n; i++) {
			String input = br.readLine();
			for(int j = 1; j <= m; j++) {
				board[i][j] = input.charAt(j-1);
			}
		}
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				if(board[i][j] == '1') {
					dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
				}else {
					dp[i][j] = 0;
				}
			}
		}
		int maxSize = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (dp[i][j] <= maxSize) continue;
				maxSize = dp[i][j];
			}
		}
		bw.append(Integer.toString(maxSize*maxSize));
		bw.flush();
	}
}