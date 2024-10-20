import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) throws IOException {
		int H = read();
		int W = read();
		int[] map = new int[W];
		for (int i = 0; i < W; i++)
			map[i] = read();

		int left = 0, right = W - 1;
		int leftMax = map[left++], rightMax = map[right--];
		int rain = 0;

		// 벽이 없으면 물이 고일 수 없도록 처리
		if (W < 3) {
			System.out.println(0);
			return;
		}

		while (left <= right) {
			if (leftMax < rightMax) {
				// 왼쪽 벽을 기준으로 물을 고임
				if (map[left] < leftMax) {
					rain += leftMax - map[left];
				} else {
					leftMax = map[left];
				}
				left++;
			} else {
				// 오른쪽 벽을 기준으로 물을 고임
				if (map[right] < rightMax) {
					rain += rightMax - map[right];
				} else {
					rightMax = map[right];
				}
				right--;
			}
		}

		System.out.println(rain);  // 고인 빗물 양을 출력
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
