import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = (int) read();
		ArrayList<Long> nums = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			nums.add(read());
		}
		Collections.sort(nums);

		int start, mid, end;
		long [] pick = new long[3];
		long min = Long.MAX_VALUE;
		for (int i = 0; i < n - 2; i++) {
			start = i;
			mid = i + 1;
			end = n - 1;
			while (mid < end) {
				long sum = nums.get(start) + nums.get(mid) + nums.get(end);
				if (min > Math.abs(sum)) {
					min = Math.abs(sum);
					pick[0] = nums.get(start);
					pick[1] = nums.get(mid);
					pick[2] = nums.get(end);
				}
				if (sum == 0) { break;}
				else if (sum > 0) { end--; }
				else { mid++; }
			}
		}
		bw.append(Long.toString(pick[0])).append(" ")
			.append(Long.toString(pick[1])).append(" ")
			.append(Long.toString(pick[2])).append(" ");
		bw.flush();
	}

	static long read() throws Exception{
		long n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) { n = System.in.read() & 15; }
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative? -n : n;
	}
}
