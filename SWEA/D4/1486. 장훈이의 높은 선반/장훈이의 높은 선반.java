import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	private static int N;  // 점원 수
	private static int B;  // 탑의 높이
	private static int[] H;  // 각 점원의 키
	private static boolean[] isSelected;

	private static int min;

	// 결과를 한 번에 출력하기 위한 StringBuilder
	private static StringBuilder sb = new StringBuilder();
	

	public static void main(String args[]) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		/**
		 * 1. 입력 파일 객체화
		 */
		int T;
		T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			String[] split = in.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			B = Integer.parseInt(split[1]);

			split = in.readLine().split(" ");
			H = new int[N];
			for (int i = 0; i < N; i++) {
				H[i] = Integer.parseInt(split[i]);
			}

			// 초기화
			isSelected = new boolean[N];
			min = Integer.MAX_VALUE;

			/**
			 * 2. 알고리즘 풀기
			 */
			subset(0);

			/**
			 * 3. 정답 출력
			 */
			sb.append(min).append("\n");
		}

		System.out.println(sb);
	}

	private static void subset(int cnt) {

		// 기저부분
		if (cnt == N) {
			
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					sum += H[i];
				}
			}
			
			if (sum >= B) {
				min = Math.min(min, sum - B);
			}
			return;
		}

		// 유도부분
		// 선택
		isSelected[cnt] = true;
		subset(cnt + 1);
		
		// 비선택
		isSelected[cnt] = false;
		subset(cnt + 1);
	}
}
