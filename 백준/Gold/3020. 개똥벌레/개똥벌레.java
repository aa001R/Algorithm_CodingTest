import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = read();
		int H = read();
		int[] imos = new int[H + 1];
		for (int i = 1; i <= N; i++) {
			int h = read();
			if (i % 2 == 0) {
				imos[0]++;
				imos[h]--;
			} else {
				imos[H - h]++;
				imos[H]--;
			}
		}
		int min = imos[0], cnt = 1;
		for (int i = 1; i < H; i++) {
			imos[i] += imos[i-1];
			if(imos[i] < min){
				min = imos[i];
				cnt = 1;
			} else if(imos[i] == min) {
				cnt++;
			}
		}
		bw.append(Integer.toString(min)).append(" ").append(Integer.toString(cnt));
		bw.flush();
	}

	public static int read() throws IOException {
		int n = System.in.read() & 15, cur;
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
