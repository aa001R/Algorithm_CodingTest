import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	private static int N;  // 숫자 개수
	private static int R;  // 연산자 개수
	private static int[] input;  // 각 연산자 개수

	private static int[] operators;  // 순열 - 연산 순서
	private static int[] numbers;  // N개의 숫자

	private static int min;  // 최소값
	private static int max;  // 최대값

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

			N = Integer.parseInt(in.readLine());
			R = N - 1;

			input = new int[4];
			String[] split = in.readLine().split(" ");
			for (int i = 0; i < 4; i++) {
				input[i] = Integer.parseInt(split[i]);
			}

			split = in.readLine().split(" ");
			numbers = new int[N];
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(split[i]);
			}

			// 초기화
			operators = new int[R];
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;

			/**
			 * 2. 알고리즘 풀기
			 */
			permutation(0, numbers[0]);

			/**
			 * 3. 정답 출력
			 */
			sb.append(max - min).append("\n");
		}

		System.out.println(sb);
	}

	private static void permutation(int cnt, int result) {

		// 기저부분
		if (cnt == R) {
			// System.out.println(Arrays.toString(operators));
			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		}

		// 유도부분
		for (int i = 0; i < 4; i++) {

			if (input[i] > 0) {
				input[i]--;  // 연산 사용

				operators[cnt] = i;  // 연산자 뽑기

				switch (operators[cnt]) {
				case 0:
					permutation(cnt + 1, result + numbers[cnt + 1]);  // 다음 연산자 뽑으러 가기
					break;

				case 1:
					permutation(cnt + 1, result - numbers[cnt + 1]);  // 다음 연산자 뽑으러 가기
					break;

				case 2:
					permutation(cnt + 1, result * numbers[cnt + 1]);  // 다음 연산자 뽑으러 가기
					break;

				case 3:
					permutation(cnt + 1, result / numbers[cnt + 1]);  // 다음 연산자 뽑으러 가기
					break;
				}

				input[i]++;  // 연산 전으로 되돌리기
			}
		}
	}
}
