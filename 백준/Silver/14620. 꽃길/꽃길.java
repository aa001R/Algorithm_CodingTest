import java.io.*;

public class Main {
	static int N, minCost;
	static int [][] garden;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read();
		garden = new int [N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				garden[i][j] = read();
			}
		}
		minCost = Integer.MAX_VALUE;
		dfs(1, 0, 0);
		bw.write(Integer.toString(minCost));
		bw.flush();
	}

	static void dfs (int r, int totalCost, int seed) {
		if (totalCost >= minCost) { return; }
		if (seed >= 3) {
			minCost = totalCost;
			return;
		}
		for (int nr = r; nr < N-1; nr++) {
			for (int nc = 1; nc < N-1; nc++) {
				if (visited[nr][nc] || visited[nr-1][nc] || visited[nr+1][nc] || visited[nr][nc-1] || visited [nr][nc+1]) { continue; }
				visited[nr][nc] = visited[nr-1][nc] = visited[nr+1][nc] = visited[nr][nc-1] = visited[nr][nc+1] = true;
				int cost = garden[nr][nc] + garden[nr-1][nc] + garden[nr+1][nc] + garden[nr][nc-1] + garden[nr][nc+1];
				dfs (nr, totalCost + cost, seed + 1);
				visited[nr][nc] = visited[nr-1][nc] = visited[nr+1][nc] = visited[nr][nc-1] = visited[nr][nc+1] = false;
			}
		}
	}

	static int read() throws Exception{
		int n = System.in.read() & 15, cur;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
