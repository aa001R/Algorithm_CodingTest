import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) throws IOException {
		int N = read();
		int[][] dayPay = new int[N][2];
		int max = 0;
		for (int i = 0; i < N; i++) {
			int p = read();
			int d = read();
			if (max < d) {
				max = d;
			}
			dayPay[i][0] = p;
			dayPay[i][1] = d;
		}
		Arrays.sort(dayPay, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0])
					return o1[1] - o2[1];
				return o2[0] - o1[0];
			}
		});

		boolean[] worked = new boolean[max + 1];
		int sumPay = 0;
		for (int i = 0; i < N; i++) {
			for(int j = dayPay[i][1]; j > 0; j--){
				if(!worked[j]){
					worked[j] = true;
					sumPay += dayPay[i][0];
					break;
				}
			}
		}
		System.out.println(sumPay);
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
