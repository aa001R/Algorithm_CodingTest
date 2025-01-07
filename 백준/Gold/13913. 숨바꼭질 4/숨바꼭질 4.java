import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int [][] time;
	static int TIME = 0, PRE = 1;
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read(); K = read();
		time = new int[2][100001];
		if (N >= K) {
			bw.append(Integer.toString(N-K)); bw.newLine();
			for (int i = N; i >= K; i--) {
				bw.append(Integer.toString(i)).append(" ");
			}
			bw.flush();
			return;
		}
		bfs();
		bw.append(Integer.toString(time[TIME][K]-1)); bw.newLine();
		ArrayList<Integer> list = new ArrayList<>();
		int cur = K;
		while (cur != N) {
			list.add(cur);
			cur = time[PRE][cur];
		}
		list.add(cur);
		for (int i = list.size() - 1; i >= 0; i--) {
			bw.append(Integer.toString(list.get(i))).append(" ");
		}
		bw.flush();
	}

	static void bfs() {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(N);
		time[TIME][N] = 1;
		time[PRE][N] = N;
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int i=0; i<3; i++) {
				int next;
				if (i == 0)       next = now + 1;
				else if (i == 1)  next = now - 1;
				else              next = now * 2;
				if (next < 0 || next > 100000) continue;
				if (time[TIME][next] == 0) { // time[next] == 0 -> 첫방문
					q.offer(next);
					time[TIME][next] = time[TIME][now] + 1;
					time[PRE][next] = now;
				}
				if (next == K) return;
			}
		}
	}
	static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) { n = System.in.read() & 15; }
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? -n : n;
	}
}
