import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int c = read(), n = read();
		int [][] items = new int[n][2];
		for (int i = 0; i < n; i++) {
			int price = read(), people = read();
			items[i][0] = price;
			items[i][1] = people;
		}
		int [] dp = new int[c + 1]; // dp[충족할 사람 수]
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 1; i <=  c; i++) {
			for (int j = 0; j < n; j++) {
				int prev = Math.max(0, i - items[j][1]);
				dp[i] = Math.min(dp[i], dp[prev] + items[j][0]);
			}
		}

		System.out.println(dp[c]);
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if(isNegative) n = System.in.read() & 15;
		while((cur = System.in.read()) > 32) n = (n << 3) + (n << 1) + (cur & 15);
		return isNegative? -n : n;
	}

}
