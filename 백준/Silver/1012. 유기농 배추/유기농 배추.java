import java.io.*;
import java.util.*;

public class Main {
	static int cnt;
	static boolean [][] farm;
	static int M, N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = read();
		for(int test = 0; test < T; test++) {
			M = read();
			N = read();
			K = read();
			farm = new boolean[N][M];
			ArrayList<int[]> cabbage = new ArrayList<>();
			for(int k = 0; k < K; k++) {
				int c = read();
				int r = read();
				farm[r][c] = true;
				cabbage.add(new int[] {r, c});
			}
			cnt = 0;
			for(int [] c : cabbage) {
				dfs(c);
			}
			bw.append(Integer.toString(cnt));
			bw.newLine();
		}
		bw.flush();
	}

	static void dfs(int [] cabbage){
		if(!farm[cabbage[0]][cabbage[1]]) {
			return;
		}
		int [][] delta = {{1, 0},{0, 1},{-1, 0},{0, -1}};
		cnt++;
		Queue<int[]> que = new ArrayDeque<>();
		que.offer(cabbage);
		while(!que.isEmpty()) {
			int [] curr = que.poll();
			for(int d = 0; d < 4; d++) {
				int r = curr[0]+delta[d][0];
				int c = curr[1]+delta[d][1];
				if(isIn(r, c) && farm[r][c]) {
					farm[r][c] = false;
					que.offer(new int[] {r, c});
				}
			}
		}
	}

	static boolean isIn(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < M);
	}

	static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) {
			n = System.in.read() & 15;
		}
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? -n : n;
	}
}
