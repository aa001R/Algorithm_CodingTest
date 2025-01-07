import java.io.*;
import java.util.*;

public class Main {
	static int [][] delta = {{9, 3, 1}, {9, 1 ,3}, {3, 9, 1}, {3, 1, 9}, {1, 3, 9}, {1, 9, 3}};
	static int [][][] dp;
	static int min;
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = read();
		int[] scv = new int[3];
		for (int i = 0; i < N; i++) {
			scv[i] = read();
		}
		Arrays.sort(scv); 
		dp = new int[61][61][61];
		min = Integer.MAX_VALUE;
		dfs(scv, 0);
		bw.write(Integer.toString(min));
		bw.flush();
	}

	static void dfs(int[] scv, int cnt) {
		if (cnt >= min) return;
		int s0 = scv[0], s1 = scv[1], s2 = scv[2];
		if (dp[s0][s1][s2] != 0 && dp[s0][s1][s2] <= cnt) return;
		dp[s0][s1][s2] = cnt;
		if (s0 == 0 && s1 == 0 && s2 == 0) {
			min = Math.min(min, cnt);
			return;
		}
		for (int[] d : delta) {
			int[] nextScv = {Math.max(s0 - d[0], 0), Math.max(s1 - d[1], 0), Math.max(s2 - d[2], 0)};
			Arrays.sort(nextScv);
			dfs(nextScv, cnt + 1);
		}
	}


	static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) { n = System.in.read() & 15; }
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? -n : n;
	}
}
