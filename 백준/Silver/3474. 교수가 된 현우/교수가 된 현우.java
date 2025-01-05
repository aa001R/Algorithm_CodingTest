import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = read();
		for (int i = 0; i < T; i++) {
			int num = read();
			int two = 0, five = 0;
			for (int j = 2; j <= num; j *= 2) {
				two += num / j;
			}
			for (int j = 5; j <= num; j *= 5) {
				five += num / j;
			}
			bw.append(Integer.toString(Math.min(two, five))).append("\n");
		}
		bw.flush();
	}

	static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) { n = System.in.read() & 15; }
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? -n : n;
	}
}
