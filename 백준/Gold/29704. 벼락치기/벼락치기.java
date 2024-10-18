import java.io.IOException;
import java.util.ArrayList;

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
		for (int i = 1; i <= n; i++) {
			int d = read();
			int m = read();
			work.add(new Work(d, m));
			totalPrice += m;
		}
		int[] dp = new int[t + 1];
		for(int i = 0; i < n; i++) {
			for(int j = t; j >= 0; j--) {
				if(work.get(i).day <= j) {
					dp[j] = Math.max(dp[j], dp[j-work.get(i).day]+work.get(i).price);
				}
			}
		}
		System.out.println(totalPrice - dp[t]);
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if (isNegative) {
			n = System.in.read() & 15;
		}
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? ~n + 1 : n;
	}
}
