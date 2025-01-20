import java.io.*;
import java.util.Arrays;

public class Main {
	static int N, minTotaPrice, selectBit;
	static int [] goalSum = new int [4];
	static int [][] ingredients;
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read();
		for (int i = 0; i < 4; i++) {
			goalSum[i] = read();
		}
		ingredients = new int [N+1][5];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 5; j++) {
				ingredients[i][j] = read();
			}
		}
		minTotaPrice = Integer.MAX_VALUE;
		comb(1, 1 << (N+1), new int [5]);
		if(selectBit != 0) {
			bw.append(Integer.toString(minTotaPrice)).append("\n");
			for (int i = 1; i <= N; i++) {
				if ((selectBit & (1 << i)) == 0) continue;
				bw.append(Integer.toString(i)).append(" ");
			}
		} else {
			bw.append("-1");
		}
		bw.flush();
	}

	static void comb(int start, int tempBit, int [] sum) {
		if (sum[4] >= minTotaPrice) return;
		if (sum[0] >= goalSum[0] && sum[1] >= goalSum[1] && sum[2] >= goalSum[2] && sum[3] >= goalSum[3]) {
			selectBit = tempBit;
			minTotaPrice = sum[4];
			return;
		}
		for (int i = start; i <= N; i++) {
			if ((tempBit & (1 << i)) != 0 ) continue;
			tempBit |= (1 << i);
			for (int j = 0; j < 5; j++) {
				sum[j] += ingredients[i][j];
			}
			comb(i+1, tempBit, sum);
			for (int j = 0; j < 5; j++) {
				sum[j] -= ingredients[i][j];
			}
			tempBit &= ~(1 << i);
		}

	}

	static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
