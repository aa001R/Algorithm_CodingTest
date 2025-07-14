import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = read();
		int [] buildings = new int[N];
		int [] cnts = new int[N];
		for (int i = 0; i < N; i++) {
			buildings[i] = read();
		}

		for (int i = 0; i < N; i++) {
			double maxGradient = Integer.MIN_VALUE;
			for (int j = i + 1; j < N; j++) {
				double gradient = (1.0 * (buildings[j] - buildings[i])) / (j - i);
				if (gradient <= maxGradient) continue;
				maxGradient = gradient;
				cnts[i]++; cnts[j]++;
			}
		}

		int max = 0;
		for (int cnt : cnts) {
			max = Math.max(max, cnt);
		}
		bw.write(Integer.toString(max)); bw.flush();
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