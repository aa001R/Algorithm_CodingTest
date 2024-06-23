import java.util.*;
import java.io.*;

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, L, W, H;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		System.out.println(binarySearch(0, Math.min(L, Math.min(W, H))));
	}

	private static double binarySearch(double low, double high) {
		double mid = .0;
		while (low < high) {
			mid = (low + high) / 2;
			if ((long) (L / mid) * (long) (W / mid) * (long) (H / mid) < N) {
				if (high == mid) break;
				high = mid;
			} else {
				if (low == mid) break;
				low = mid;
			}
		}
		return low;
	}
}