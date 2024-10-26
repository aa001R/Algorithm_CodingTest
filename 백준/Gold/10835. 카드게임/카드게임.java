import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int N;
	static int[] left, right;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read();
		left = new int[N];
		right = new int[N];
		for(int i = 0; i < N; i++){
			left[i] = read();
		}
		for(int i = 0; i < N; i++){
			right[i] = read();
		}
		dp = new int[N][N];
		for (int[] arr : dp) {
			Arrays.fill(arr, -1);
		}
		bw.write(Integer.toString(topDown(0, 0)));
		bw.flush();
	}

	public static int topDown(int l, int r){
		if (l == N || r == N) { // 기저 사례
			return 0;
		}

		if (dp[l][r] != -1) {
			return dp[l][r];
		}
		dp[l][r] = Math.max(topDown(l + 1, r), topDown(l + 1, r + 1)); // 왼쪽 카드 제거

		if (left[l] > right[r]) { // 오른쪽 카드 제거
			dp[l][r] = Math.max(dp[l][r], topDown(l, r+1) + right[r]);
		}
		return dp[l][r];
	}

	public static int read() throws IOException {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) {
			n = System.in.read() & 15;
		}
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? ~n : n;
	}
}
