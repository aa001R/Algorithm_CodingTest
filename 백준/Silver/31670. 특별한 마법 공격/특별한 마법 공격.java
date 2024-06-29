import java.util.*;
import java.io.*;

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N; 
	static long dp[][];
	static int KILL = 1, ALIVE = 0;
	public static void main(String[] args) throws IOException
	{
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		dp = new long[N][2];
		dp[0][KILL] = Integer.parseInt(st.nextToken());
		for(int i = 1; i < N; i++) {
			int cur = Integer.parseInt(st.nextToken());
			dp[i][ALIVE] = dp[i-1][KILL];
			dp[i][KILL] = Math.min(dp[i-1][ALIVE] + cur, dp[i-1][KILL] + cur);
		}
		bw.append(Long.toString(Math.min(dp[N-1][0], dp[N-1][1])));
		bw.flush();
	}
}