import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	private static final int NO = -1;  // 약품 X
	private static final int A = 0;  // 약품 A
	private static final int B = 1;  // 약품 B

	private static int D;  // 두께
	private static int W;  // 가로 크기
	private static int K;  // 합격기준
	private static int[][] cells;  // 보호필름
	private static int min;  // 각 테스트 케이스마다의 정답 중 최소값

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T;
		T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			String[] split = in.readLine().split(" ");
			D = Integer.parseInt(split[0]);
			W = Integer.parseInt(split[1]);
			K = Integer.parseInt(split[2]);

			cells = new int[D][W];
			for (int d = 0; d < D; d++) {
				split = in.readLine().split(" ");
				for (int w = 0; w < W; w++) {
					cells[d][w] = Integer.parseInt(split[w]);
				}
			}

			// 초기화
			min = Integer.MAX_VALUE;

			subset(0, 0);
			
			sb.append(min).append("\n");
		}
		
		System.out.println(sb);
	}

	// cnt: 선택 횟수
	// usedCnt: 약품 선택 횟수
	private static void subset(int cnt, int usedCnt) {

		// 기저부분
		if (cnt == D) {
			// 합격기준 검사
			int passCnt = 0;  // 통과된 열 개수
			
			for (int w = 0; w < W; w++) {
				int k = 0;  // 연속된 개수
				int prev = NO;
				boolean isPass = false;
				
				for (int d = 0; d < D; d++) {
					int current = cells[d][w];
					
					// 처음
					if (k == 0) {
						prev = current;
						k++;
					}
					// 이전 값과 같다면
					else if (current == prev) {
						k++;
					}
					// 이전 값과 다르다면
					else if (current != prev) {
						prev = current;
						k = 1;
					}
					
					if (k == K) {
						isPass = true;
						break;
					}
				}
				
				if (isPass) {
					passCnt++;  // 통과 횟수 증가시키고 다음 열 검사하러가기
				}
				else {
					break;  // 다음 열 검사 안함
				}
			}
			
			// 모든 열이 합격기준에 통과했다면
			if (passCnt == W) {
				min = Math.min(min, usedCnt);
			}
			
			return;  // 다음 경우의 수로 가기 위해 리턴
		}

		// 유도부분
		// 약품 처리할 행만 백업
		int[] original = new int[W];
		for (int w = 0; w < W; w++) {
			original[w] = cells[cnt][w];
		}

		// 경우1: 약품 사용 안함
		subset(cnt + 1, usedCnt);

		// 경우2: 약품 A 사용
		for (int w = 0; w < W; w++) {
			cells[cnt][w] = A;
		}
		subset(cnt + 1, usedCnt + 1);

		// 경우3: 약품 B 사용
		for (int w = 0; w < W; w++) {
			cells[cnt][w] = B;
		}
		subset(cnt + 1, usedCnt + 1);
		
		// 복원
		cells[cnt] = original;
	}
}
