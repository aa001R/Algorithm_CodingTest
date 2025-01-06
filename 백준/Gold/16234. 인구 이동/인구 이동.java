import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
	static int N, L, R;
	static AtomicInteger[][] nation;
	static 	int[][] delta = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read();
		L = read();
		R = read();
		nation = new AtomicInteger[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				nation[r][c] = new AtomicInteger(read());
			}
		}

		int day = 0;
		while (true) {
			boolean move = false;
			boolean[][] visited = new boolean[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (visited[r][c]) continue;
					boolean isUnion = false;
					for (int d = 0; d < 2 && !isUnion; d++){
						isUnion |= canMove(r + delta[d][0], c + delta[d][1], nation[r][c].get(), visited);
					}
					if (!isUnion) continue;
					bfs(r, c, visited);
					move = true;
				}
			}
			if (!move) {break;}
			day++;
		}

		bw.write(Integer.toString(day));
		bw.flush();
	}

	static void bfs(int r, int c, boolean[][] visited) {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.offer(new int[] { r, c });
		visited[r][c] = true;
		AtomicInteger union = new AtomicInteger(0);
		int sum = 0, cnt = 0;

		while (!que.isEmpty()) {
			int[] curr = que.poll();
			int curNationPeople = nation[curr[0]][curr[1]].get();
			sum += curNationPeople;
			nation[curr[0]][curr[1]] = union;
			cnt++;

			for (int d = 0; d < 4; d++) {
				int nr = curr[0] + delta[d][0];
				int nc = curr[1] + delta[d][1];
				if (!canMove(nr, nc, curNationPeople, visited)) continue;
				que.offer(new int[] { nr, nc });
				visited[nr][nc] = true;
			}
		}
		int avg = sum / cnt;
		union.set(avg);
	}

	private static boolean canMove(int nr, int nc, int prev, boolean[][] visited){
		if (!isIn(nr, nc) || visited[nr][nc]) return false;
		int gap = Math.abs(prev - nation[nr][nc].get());
		return L <= gap && gap <= R;
	}

	static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
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
