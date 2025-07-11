import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int N = read();
		boolean[] exists = new boolean[1_000_001];
		int[] nums = new int[N];

		for (int i = 0; i < N; i++) {
			nums[i] = read();
			exists[nums[i]] = true; // 해당 수가 존재한다고 표시
		}

		int x = read();
		int count = 0;

		for (int i = 0; i < N; i++) {
			int target = x - nums[i];
			if (target <= 0 || target > 1_000_000) continue;

			if (exists[target]) {
				count++;
			}
		}

		// (a, b)와 (b, a)를 모두 세었으므로 2로 나눔
		System.out.println(count / 2);
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if (isNegative) n = System.in.read() & 15;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? -n : n;
	}
}
