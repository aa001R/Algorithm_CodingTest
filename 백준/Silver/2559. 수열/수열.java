import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = read();
		int K = read();
		int [] temperature = new int[N];
		for (int i = 0; i < N; i++) {
			temperature[i] = read();
		}
		int max = 0;
		for (int k = 0; k < K; k++) {
			max += temperature[k];
		}
		int sum = max;
		for (int i = K; i < N; i++) {
			sum += temperature[i] - temperature[i - K];
			if (sum > max) {
				max = sum;
			}
		}
		bw.write(Integer.toString(max));
		bw.flush();
	}
	static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) { n = System.in.read() & 15; }
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative? -n : n;
	}
}
