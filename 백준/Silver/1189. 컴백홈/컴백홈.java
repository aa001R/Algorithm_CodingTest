import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
	static int R, C, K, cnt = 0;
	static char [][] road;
	static boolean [][] visited;
	static int [][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		R = read(); C = read(); K = read();
		road = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			road[i] = br.readLine().toCharArray();
		}
		visited[R-1][0] = true;
		dfs(R-1, 0, 1);
		bw.write(Integer.toString(cnt));
		bw.flush();
	}

	static void dfs (int r, int c, int distance) {
		if (distance > K) return;
		if (distance == K && r == 0 && c == C-1) {
			cnt++;
			return;
		}
		for (int d = 0; d < 4; d++) {
			int nr = r + delta[d][0];
			int nc = c + delta[d][1];
			if (isOut(nr, nc)) continue;
			if (road[nr][nc] == 'T') continue;
			if (visited[nr][nc]) continue;
			visited[nr][nc] = true;
			dfs(nr, nc, distance + 1);
			visited[nr][nc] = false;
		}
	}

	static boolean isOut(int nr, int nc) {
		return nr < 0 || nc < 0 || nr >= R || nc >= C;
	}

	static int read() throws Exception{
		int n = System.in.read() & 15, cur;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
