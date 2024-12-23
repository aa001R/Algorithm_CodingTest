import java.io.*;
import java.util.*;

public class Main {
	static int N, max;
	static int [][] area;
	static boolean[] check = new boolean[101];
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read();
		area = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				area[i][j] = read();
			}
		}
		max = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(check[area[i][j]]) continue;
				check[area[i][j]] = true;
				rainHight(area[i][j]);
			}
		}
		bw.write(Integer.toString(max));
		bw.flush();
	}

	private static void rainHight(int rain) {
		int cnt = 0;
		boolean [][] areaAfterRain = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(areaAfterRain[i][j] || area[i][j] <= rain ) continue;
				cnt++;
				bfs(i, j, areaAfterRain, rain);
			}
		}
		max = Math.max(max, cnt);
	}

	private static void bfs(int i, int j, boolean[][] areaAfterRain, int rain) {
		int [][] delta = {{0, 1},{0, -1},{1, 0},{-1, 0}};
		ArrayDeque<int []> que = new ArrayDeque<>();
		que.offer(new int[] {i, j});
		areaAfterRain[i][j] = true;
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			for(int d = 0; d < 4; d++) {
				int r = cur[0] + delta[d][0];
				int c = cur[1] + delta[d][1];
				if(isOut(r, c)) continue;
				if(areaAfterRain[r][c]) continue;
				areaAfterRain[r][c] = true;
				if(area[r][c]<=rain) continue;
				que.offer(new int[] {r,c});
			}
		}
	}

	private static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
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
