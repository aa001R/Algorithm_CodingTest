import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int N = read();
		ArrayList<Integer> nums = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			nums.add(read());
		}
		int x = read();
		Collections.sort(nums);
		int left = 0; int right = nums.size() - 1;
		int cnt = 0;
		while(left < right) {
			int sum = nums.get(left) + nums.get(right);
			if (sum == x) {cnt++;}
			if (sum <= x) {left++;}
			else { right--; }
		}
		System.out.println(cnt);
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if (isNegative) n = System.in.read() & 15;
		while((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative? -n : n;
	}
}
