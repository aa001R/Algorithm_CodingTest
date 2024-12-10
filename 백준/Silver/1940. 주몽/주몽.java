import java.util.*;
import java.io.*;
public class Main {
	static int N, M, cnt = 0;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException {
		N = read();
		M = read();
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(read());
		}
		Collections.sort(list);
		twoPoint(0, N-1);
		System.out.println(cnt);
	}

	static void twoPoint(int left, int right) {
		while (left < right) {
			int sum = list.get(left) + list.get(right);
			if (sum > M) {
				right--;
			} else if (sum == M) {
				cnt++;
				left++; right--;
			} else {
				left++;
			}
		}
	}

	static int read() throws IOException {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) { n = System.in.read() & 15; }
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? -n : n;
	}
}
