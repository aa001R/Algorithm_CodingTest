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
		bw.write(Integer.toString(solve(N, H, M, ladder)));
		bw.flush();
	}

	private static int solve(int n, int h, int m, boolean[][] ladder) {
		if (countOddLines(ladder, n, h) > 3) {
			//홀수개 가로선이 이미 있는 칸이 3개보다 많으면 각 칸에 +1 가로선을 해야함 => 정답이 3보다 큼
			return -1;
		}
		// 추가할 수 있는 사다리는 최대 3개
		// 기존 가로선이 홀수 개라면 홀수 개 추가 / 짝수 개면 최대 짝수 개 추가 
		// 최소 개수이니 작은 값부터 확인 (홀수 : 1 -> 3 / 짝수 : 0 -> 2 순서로 확인)
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
				if (ladder[y][x]) isOdd = !isOdd;
			}
			if (isOdd) oddCount++;
		}
		return oddCount;
	}

	private static boolean tryAddLadders(int n, int h, boolean[][] ladder, int limit) {
		return dfs(n, h, 1, 1, 0, limit, ladder);
	}

	private static boolean dfs(int n, int h, int y, int x, int count, int limit, boolean[][] ladder) {
		if (count == limit) return validateLadders(n, h, ladder);

		// 현재 줄에서 이후로 가로선을 긋기
		for (int nx = x; nx < n; nx++) {
			if (addLadderAndCheck(n, h, y, nx, count, limit, ladder)) return true;
		}

		// 다음 줄에서 처음부터 가로선 긋기
		for (int ny = y + 1; ny <= h; ny++) {
			for (int nx = 1; nx < n; nx++) {
				if (addLadderAndCheck(n, h, ny, nx, count, limit, ladder)) return true;
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
