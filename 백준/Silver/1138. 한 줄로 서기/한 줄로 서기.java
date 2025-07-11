import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = read();
		int [] higths = new int[N];
		List<Integer> orders = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			higths[i] = read();
		}
		for (int i = N - 1; i >= 0; i--) {
			int order = i+1;
			if (higths[i] >= orders.size()) {
				orders.add(order);
			} else {
				orders.add(higths[i], order);
			}
		}
		for (int n : orders) {
			bw.append(Integer.toString(n)).append(" ");
		}
		bw.flush();
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
