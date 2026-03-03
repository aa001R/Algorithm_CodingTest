import java.util.*;
import java.io.*;

public class Main {
	static final int INVALID = -1; // -1 = 지뢰 설치 불가능(미방문) 상태
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			String numberRow = br.readLine();
			String blockRow  = br.readLine();

			int[] need   = new int[N];
			int[] isMine = new int[N]; // 1=지뢰 확정(*), -1=자유(#)
			for (int i = 0; i < N; i++) {
				need[i]   = numberRow.charAt(i) - '0';
				isMine[i] = (blockRow.charAt(i) == '*') ? 1 : -1;
			}

			if (N == 1) {
				bw.append(Integer.toString(need[0])).append("\n");
				continue;
			}

			// dp[a][b] = [직전 셀 지뢰 여부][현재 셀 지뢰 여부] = 상태의 최대 지뢰 수
			int[][] dp = new int[2][2]; // 0=지뢰없음, 1=지뢰있음
			for (int[] row : dp) Arrays.fill(row, INVALID);

			for (int curMine = 0; curMine <= 1; curMine++) {
				if (isMine[0] == 1 && isMine[0] != curMine) continue;
				dp[0][curMine] = curMine;
			}

			for (int col = 1; col < N; col++) {
				int[][] newDp = new int[2][2];
				for (int[] row : newDp) Arrays.fill(row, INVALID);

				for (int twoBackMine = 0; twoBackMine <= 1; twoBackMine++) {
					for (int oneBackMine = 0; oneBackMine <= 1; oneBackMine++) {
						if (dp[twoBackMine][oneBackMine] == INVALID) continue;

						for (int curMine = 0; curMine <= 1; curMine++) {
							if (isMine[col] == 1 && isMine[col] != curMine) continue;

							int constraintSum = (col == 1) ? (oneBackMine + curMine) : (twoBackMine + oneBackMine + curMine);
							if (constraintSum != need[col - 1]) continue;
							if (col == N - 1 && (oneBackMine + curMine) != need[N - 1]) continue;

							int newVal = dp[twoBackMine][oneBackMine] + curMine;
							newDp[oneBackMine][curMine] = newVal;
						}
					}
				}
				dp = newDp;
			}

			int totalMines = 0;
			for (int a = 0; a <= 1; a++)
				for (int b = 0; b <= 1; b++)
					if (dp[a][b] != INVALID) totalMines = Math.max(totalMines, dp[a][b]);

			bw.append(Integer.toString(totalMines)).append("\n");
		}
		bw.flush();
	}
}
