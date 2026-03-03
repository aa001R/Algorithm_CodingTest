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
			char[] numberRow = br.readLine().toCharArray();
			char[] blockRow  = br.readLine().toCharArray();

			int[] need   = new int[N];
			int[] isMine = new int[N]; // 1=지뢰 확정(*), -1=자유(#)
			for (int i = 0; i < N; i++) {
				need[i]   = numberRow[i] - '0';
				isMine[i] = (blockRow[i] == '*') ? 1 : -1;
			}

			// N=1 예외처리: mine[0] = need[0]
			if (N == 1) {
				bw.append(Integer.toString(need[0])).append("\n");
				continue;
			}

			// dp[a][b] = [직전 셀 지뢰 여부][현재 셀 지뢰 여부] = 상태의 최대 지뢰 수
			int[][] dp = new int[2][2]; // 0=지뢰없음, 1=지뢰있음
			for (int[] row : dp) Arrays.fill(row, INVALID); // -1 = 지뢰 설치 미방문 or 불가능 상태

			// 첫 번째 셀 초기화: 직전 셀 없으므로 지뢰없음(0) 고정, 첫 번째 셀 지뢰 여부(curMine)만 결정
			for (int curMine = 0; curMine <= 1; curMine++) {
				// *로 확정된 셀인데 지뢰 아님(0)으로 설정하려는 경우를 차단
				if (isMine[0] == 1 && isMine[0] != curMine) continue;
				dp[0][curMine] = curMine;
			}

			for (int col = 1; col < N; col++) {
				int[][] newDp = new int[2][2];
				for (int[] row : newDp) Arrays.fill(row, INVALID);

				for (int twoBackMine = 0; twoBackMine <= 1; twoBackMine++) { // 이전 위치 지뢰여부 (0, 1)
					for (int oneBackMine = 0; oneBackMine <= 1; oneBackMine++) { // 현재 위치 지뢰여부 (0, 1)
						if (dp[twoBackMine][oneBackMine] == INVALID) continue; // 불가능한 경우의 수

						for (int curMine = 0; curMine <= 1; curMine++) {
							if (isMine[col] == 1 && isMine[col] != curMine) continue; // col 지뢰 확정 => [][0]인 경우 제외

							// 위치 col-1의 제약 검사
							// col=1: 윈도우 [0,1] = curMine+nextMine
							// col>=2: 윈도우 [col-2,col-1,col] = preMine+curMine+nextMine
							int constraintSum = (col == 1) ? (oneBackMine + curMine) : (twoBackMine + oneBackMine + curMine);
							if (constraintSum != need[col - 1]) continue;

							// 마지막 셀: 위치 N-1의 제약도 검사 (윈도우 [N-2, N-1] = curMine+nextMine)
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
