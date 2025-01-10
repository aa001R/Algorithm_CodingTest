import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = read(), M = read(), H = read();
		boolean [][] ladder = new boolean[H+1][N+1];
		for(int m = 0; m < M; m++) {
			int a = read();
			int b = read();
			ladder[a][b] = true;
		}
		bw.write(Integer.toString(addLadder(N, H, M, ladder)));
		bw.flush();
	}

	private static int addLadder(int n, int h, int m, boolean[][] ladder) {
		if (countOddLines(ladder, n, h) > 3) {
			return -1;
		}
		for (int limit = m % 2; limit < 4; limit += 2) {
			if (tryAddLadders(n, h, ladder, limit)) {
				return limit;
			}
		}
		return -1;
	}

	private static int countOddLines(boolean[][] ladder, int n, int h) {
		int oddCount = 0;
		for (int x = 1; x < n; x++) {
			boolean isOdd = false;
			for (int y = 1; y <= h; y++) {
				if (ladder[y][x]) {
					isOdd = !isOdd;
				}
			}
			if (isOdd) {
				oddCount++;
			}
		}
		return oddCount;
	}

	private static boolean tryAddLadders(int n, int h, boolean[][] ladder, int limit) {
		return dfs(n, h, 1, 1, 0, limit, ladder);
	}

	private static boolean dfs(int n, int h, int y, int x, int count, int limit, boolean[][] ladder) {
		if (count == limit) {
			return validateLadders(n, h, ladder);
		}

		for (int nx = x; nx < n; nx++) {
			if (addLadderAndCheck(n, h, y, nx, count, limit, ladder)) {
				return true;
			}
		}

		for (int ny = y + 1; ny <= h; ny++) {
			for (int nx = 1; nx < n; nx++) {
				if (addLadderAndCheck(n, h, ny, nx, count, limit, ladder)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean addLadderAndCheck(int n, int h, int y, int x, int count, int limit, boolean[][] ladder) {
		if (ladder[y][x] || ladder[y][x - 1] || ladder[y][x + 1]) {
			return false;
		}

		ladder[y][x] = true;
		boolean result = dfs(n, h, y, x, count + 1, limit, ladder);
		ladder[y][x] = false;

		return result;
	}

	private static boolean validateLadders(int n, int h, boolean[][] ladder) {
		for (int x = 1; x <= n; x++) {
			int current = x;
			for (int y = 1; y <= h; y++) {
				if (ladder[y][current]) {
					current++;
				} else if (current > 1 && ladder[y][current - 1]) {
					current--;
				}
			}
			if (current != x) {
				return false;
			}
		}
		return true;
	}

	static int read() throws Exception{
		int n = System.in.read() & 15, cur;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
