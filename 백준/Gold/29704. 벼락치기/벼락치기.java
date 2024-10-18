import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static class Work {
		int day;
		int price;

		public Work(int day, int price) {
			this.day = day;
			this.price = price;
		}
	}

	public static void main(String[] args) throws IOException {
		int n = read();
		int t = read();
		int totalPrice = 0;

		ArrayList<Work> work = new ArrayList<>();
		work.add(new Work(0, 0));
		for (int i = 1; i <= n; i++) {
			int d = read();
			int m = read();
			work.add(new Work(d, m));
			totalPrice += m;
		}

		Collections.sort(work, (w1, w2) -> w1.day - w2.day);

		int[][] dp = new int[n + 1][t + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= t; j++) {
				if (work.get(i).day <= j) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - work.get(i).day] + work.get(i).price);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		System.out.println(totalPrice - dp[n][t]);
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if (isNegative) {
			n = System.in.read() & 15;
		}
		while((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative? ~n + 1 : n;
	}
}
