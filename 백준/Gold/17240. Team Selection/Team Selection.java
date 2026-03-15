import java.io.*;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		int n = read();
		int limit = 1 << 5;
		int [][] abilities = new int[n][5];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 5; j++) {
				abilities[i][j] = read();
			}
		}
		int [] dp = new int[limit];
		Arrays.fill(dp, Integer.MIN_VALUE);
		dp[0] = 0;
		for (int i = 0; i < n; i++) {
			for (int mask = limit - 1; mask >= 0 ; mask--) {
				for (int j = 0; j < 5; j++) {
					if ((mask & (1 << j)) == 0) continue;
					dp[mask] = Math.max(dp[mask], dp[mask ^ (1 << j)] + abilities[i][j]);
				}
			}
		}
		System.out.println(dp[limit-1]);
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if(isNegative) n = System.in.read() & 15;
		while((cur = System.in.read()) > 32) n = (n << 3) + (n << 1) + (cur & 15);
		return isNegative? -n : n;
	}
}
