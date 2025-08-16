import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int a1 = read(), a0 = read();
		int c = read(), n0 = read();
		if (a1 < c && a1 * n0 + a0 <= c * n0) {
			// a1 < c인 경우: 기울기가 작으므로 충분히 큰 n 에서는 항상 성립
			// => n0에서만 확인하면 됨
			System.out.println(1);
		} else if (a1 == c && a0 <= 0) { // a0 <= 0이어야 함
			System.out.println(1);
		} else { // a1 > c인 경우: 기울기가 더 크므로 n이 커질수록 조건 위반
			System.out.println(0);
		}
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if (isNegative) n = System.in.read() & 15;
		while ((cur = System.in.read()) > 32) n = (n << 3) + (n << 1) + (cur & 15);
		return isNegative ? -n : n;
	}
}
