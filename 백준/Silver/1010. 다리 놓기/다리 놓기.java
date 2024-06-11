import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		dp = new int[30][30];
		
		for(int i = 0; i < 30; i++) {
			for(int j = 0; j <= i/2; j++) {
				if(j == 0 || j == i)  dp[i][j] = 1;
				else  dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				dp[i][i-j] = dp[i][j];
			}
		}

		int T = Integer.parseInt(br.readLine());
		for(int test = 0; test < T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			bw.append(Integer.toString(dp[M][N]));
			bw.newLine();
		}
		bw.flush();
	}
}