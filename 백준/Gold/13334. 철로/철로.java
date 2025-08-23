import java.io.*;
import java.util.*;

public class Main {
	static class Event {
		final int railEnd, change;
		Event(int railEnd, int change) {
			this.railEnd = railEnd;
			this.change = change;
		}
	}
	public static void main(String[] args) throws IOException {
		int N = read();
		int[][] data = new int[N][2];
		for (int i = 0; i < N; i++) {
			int home = read(), office = read();
			data[i][0] = Math.min(home, office);
			data[i][1] = Math.max(home, office);
		}
		int d = read();

		PriorityQueue<Event> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.railEnd));
		for (int i = 0; i < N; i++) {
			if (data[i][1] - data[i][0] > d) continue;
			pq.offer(new Event(data[i][1], 1));
			pq.offer(new Event(data[i][0] + d + 1, -1));
		}
		int max = 0, cnt = 0;
		while (!pq.isEmpty()) {
			Event e = pq.poll();
			cnt += e.change;
			while (!pq.isEmpty() && pq.peek().railEnd == e.railEnd) {
				cnt += pq.poll().change;
			}
			max = Math.max(max, cnt);
		}
		System.out.println(max);
	}

	// 빠른 입력을 위한 read 함수
	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if (isNegative) n = System.in.read() & 15;
		while ((cur = System.in.read()) > 32) n = (n << 3) + (n << 1) + (cur & 15);
		return isNegative ? -n : n;
	}
}
