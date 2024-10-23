import java.io.IOException;

public class Main {

	static int N, M;
	static long[] A = new long[101010];
	static long[] B = new long[101010];
	static long[] height = new long[101010];
	static long[] height2 = new long[101010];

	public static void main(String[] args) throws IOException {
		N = read(); // 사용자 정의 read 메서드를 사용
		M = read(); // 사용자 정의 read 메서드를 사용

		for (int i = 1; i <= M; i++) {
			A[i] = read();
			B[i] = read();
			height[i] = height2[i] = N + 1;
		}

		// 왼쪽에서 최소값을 구해 height 배열 업데이트
		long mn = N + 1;
		for (int i = 1; i <= M; i++) {
			if (B[i] == N) {
				mn = Math.min(mn, A[i]);
			}
			height[i] = mn;
		}

		// 오른쪽에서 최소값을 구해 height 배열 업데이트
		mn = N + 1;
		for (int i = M; i >= 1; i--) {
			if (B[i] == N) {
				mn = Math.min(mn, A[i]);
			}
			height[i] = Math.max(mn, height[i]);
		}

		long ans = 0;

		// 높이를 바탕으로 결과 계산
		for (int i = 1; i <= M; i++) {
			if (B[i] != N) {
				ans += N + 1 - height[i];
			} else {
				ans += A[i] - height[i];
			}
		}

		// 연속된 범위에 대한 추가 처리
		long start = 0;
		for (int i = 1; i <= M; i++) {
			if (B[i] != 0 && B[i] != N) {
				if (start != 0) {
					if (A[i - 1] > B[i] || A[i] > B[i - 1]) {
						ans += get(start, i - 1);
						start = 0;
					}
				}
				if (start == 0) {
					start = i;
				}
			} else {
				if (start != 0) {
					ans += get(start, i - 1);
					start = 0;
				}
			}
		}
		if (start != 0) {
			ans += get(start, M);
		}

		System.out.println(ans);
	}

	// 특정 범위에 대한 최소 높이를 계산하고 결과를 반환하는 함수
	static long get(long left, long right) {
		long mn = N + 1;

		// 왼쪽에서 최소값 계산 후 height2 배열 업데이트
		for (long i = left; i <= right; i++) {
			mn = Math.min(mn, A[(int)i]);
			height2[(int)i] = mn;
		}

		mn = N + 1;
		// 오른쪽에서 최소값 계산 후 height2 배열 업데이트
		for (long i = right; i >= left; i--) {
			mn = Math.min(mn, A[(int)i]);
			height2[(int)i] = Math.max(mn, height2[(int)i]);
		}

		// A[i]와 height2[i]의 차이를 계산하여 sum에 누적
		long sum = 0;
		for (long i = left; i <= right; i++) {
			sum += A[(int)i] - height2[(int)i];
		}

		// B[i] 값과 비교하여 sum에서 차이를 뺌
		for (long i = left; i <= right; i++) {
			if (B[(int)i] >= height[(int)i]) {
				sum -= B[(int)i] - Math.max(height[(int)i], height2[(int)i]) + 1;
			}
		}

		return sum;
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if (isNegative) {
			n = System.in.read() & 15;
		}
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? ~n + 1 : n;
	}
}
