import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char [][] classroom;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read(); M = read();
		classroom = new char[N][M];
		int jY = read() - 1, jX = read() - 1, thiefY = read() - 1, thiefX = read() - 1;
		for (int i = 0; i < N; i++) {
			classroom[i] = br.readLine().toCharArray();
		}
		bw.append(Integer.toString(bfs(jY, jX, thiefY, thiefX))); bw.newLine();
		bw.flush();
	}

	static int bfs(int y, int x, int thiefY, int thiefX) {
		ArrayDeque<int []> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		q.offer(new int[] {y, x});
		visited[y][x] = true;
		classroom[y][x] = 0;
		int [][] delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		int jumpCnt = 0;
		while (!q.isEmpty()) {
			jumpCnt++;
			for (int size = q.size(); size > 0; size--) {
				int [] cur = q.poll();
				ArrayDeque<int []> tempQ = new ArrayDeque<>();
				for (int d = 0; d < 4; d++) {
					int nextY = cur[0], nextX = cur[1];
					while (true) {
						nextY += delta[d][0]; nextX += delta[d][1];
						if (isOut(nextY, nextX) || visited[nextY][nextX]) break;
						visited[nextY][nextX] = true;
						if (nextY == thiefY && nextX == thiefX) return jumpCnt;
						if (classroom[nextY][nextX] == '1') {
							q.offer(new int[] {nextY, nextX});
							break;
						}
						tempQ.offer(new int[] {nextY, nextX});
					}
				}
				while (!tempQ.isEmpty()) {
					int [] chainBomb = tempQ.poll();
					for (int d = 0; d < 4; d++) {
						int nextY = chainBomb[0], nextX = chainBomb[1];
						while (true) {
							nextY += delta[d][0]; nextX += delta[d][1];
							if (isOut(nextY, nextX) || visited[nextY][nextX]) break;
							visited[nextY][nextX] = true;
							if (nextY == thiefY && nextX == thiefX) return jumpCnt;
							if (classroom[nextY][nextX] == '1') {
								q.offer(new int[] {nextY, nextX});
								break;
							}
							tempQ.offer(new int[] {nextY, nextX});
						}
					}
				}
			}
		}
		return jumpCnt;
	}

	static boolean isOut(int y, int x){
		return y < 0 || y >= N || x < 0 || x >= M;
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
