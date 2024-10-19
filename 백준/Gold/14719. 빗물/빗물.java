import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int H = read();
		int W = read();
		int[] map = new int[W];
		int rain = 0;
		for (int i = 0; i < W; i++)
			map[i] = read();

		ArrayDeque<Integer> stack = new ArrayDeque<>();
		int left = 0, right = 0;
		for (int i = 0; i < W; i++) {
			if (stack.isEmpty()) {
				if (map[i] != 0) {
					left = map[i];
					stack.push(left);
				}
			} else if (left > map[i]) {
				stack.push(map[i]);
			} else {
				while (!stack.isEmpty()) {
					rain += (left - stack.pop());
				}
				left = map[i];
				stack.push(left);
			}
		}
		if(!stack.isEmpty()) {
			right = stack.pop();
		}
		while (!stack.isEmpty()) {
			int cur = stack.pop();
			if (right >= cur) {
				rain += (right - cur);
			} else {
				right = cur;
			}
		}
		bw.write(rain + "");
		bw.flush();
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
