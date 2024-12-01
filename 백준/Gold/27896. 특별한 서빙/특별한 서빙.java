import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		int N = read();
		int M = read();

		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		long sum = 0, eggPlantCnt = 0;
		for (int i = 0; i < N; i++) {
			int cur = read();
			pq.add(cur);
			sum += cur;
			while (!pq.isEmpty() && sum >= M) {
				eggPlantCnt++;
				sum -= pq.poll() * 2;
			}
		}
		System.out.println(eggPlantCnt);
	}

	static int read() throws IOException {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) { n = System.in.read() & 15; }
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative? -n : n;
	}
}
