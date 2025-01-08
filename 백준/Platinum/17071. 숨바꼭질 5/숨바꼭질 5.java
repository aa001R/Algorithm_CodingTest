import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static boolean[][] visited = new boolean[2][500001];
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read(); K = read();
		if (N == K) {
			bw.append(Integer.toString(0));
			bw.flush();
			return;
		}
		bw.append(Integer.toString(bfs())); bw.newLine();
		bw.flush();
	}

	static int bfs() {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(N);
		visited[0][N] = true;
		int elapsed = 0, flag;
		while (!q.isEmpty()) {
			elapsed++;
			flag = elapsed % 2;
			if (K + elapsed > 500_000) return - 1;
			K += elapsed;
			for (int size = q.size(); size > 0; size--) {
				int now = q.poll();
				for (int i=0; i<3; i++) {
					int next;
					if (i == 0)       next = now + 1;
					else if (i == 1)  next = now - 1;
					else              next = now * 2;
					if (K == next) return elapsed;
					if (next < 0 || next > 500000 || visited[flag][next]) continue;
					q.offer(next);
					visited[flag][next] = true;
				}
			}
			if (visited[flag][K]) return elapsed;
		}
		return -1;
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
