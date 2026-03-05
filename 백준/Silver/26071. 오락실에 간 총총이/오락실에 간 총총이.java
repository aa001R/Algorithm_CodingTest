import java.util.*;
import java.io.*;

public class Main {
	static int N, min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		char[][] board = new char[N][N];
		int maxR = -1, maxC = -1, minR = N, minC = N;
		for (int r = 0; r < N; r++) {
			String input = br.readLine();
			for (int c = 0; c < N; c++){
				board[r][c] = input.charAt(c);
				if (board[r][c] == '.') continue;
				maxR = Math.max(maxR, r);
				minR = Math.min(minR, r);
				maxC = Math.max(maxC, c);
				minC = Math.min(minC, c);
			}
		}
		if (maxR == minR && maxC == minC) {
			System.out.println("0");
			return;
		}
		for (int d = 0; d < 4; d++) {
			play(maxR, maxC, minR, minC, d);
		}

		System.out.println(min);
	}
	static void play(int maxR, int maxC, int minR, int minC, int d){
		int cnt = 0;
		int targetR = (d / 2) * (N - 1);
		int targetC = (d % 2) * (N - 1);
		cnt += moveCount(maxR, minR, targetR);
		cnt += moveCount(maxC, minC, targetC);
		min = Math.min(min, cnt);
	}

	static int moveCount(int max, int min, int target) {
		if (max == min) return 0;
		int maxMove = Math.abs(target - max);
		int minMove = Math.abs(target - min);
		return Math.max(maxMove, minMove);
	}
}
