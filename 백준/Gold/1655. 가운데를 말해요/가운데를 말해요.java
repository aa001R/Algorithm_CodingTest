import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = read();
		int middle = read();
		bw.append(Integer.toString(middle));
		bw.newLine();

		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

		for (int i = 1; i < n; i++) {
			int num = read();

			if (num < middle) {
				maxHeap.offer(num);
			} else {
				minHeap.offer(num);
			}

			if (minHeap.size() - maxHeap.size() > 1) {
				maxHeap.add(middle);
				middle = minHeap.poll();
			}

			if (maxHeap.size() - minHeap.size() >= 1) {
				minHeap.add(middle);
				middle = maxHeap.poll();
			}

			bw.append(Integer.toString(middle));
			bw.newLine();
		}
		bw.flush();
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
