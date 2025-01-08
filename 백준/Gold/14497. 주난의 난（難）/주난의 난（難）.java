import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char [][] classroom;
	static ArrayDeque<int []> q, chainQ;
	static boolean[][] visited;
	static int [][] delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
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
		q = new ArrayDeque<>();
		visited = new boolean[N][M];
		q.offer(new int[] {y, x});
		visited[y][x] = true;
		int jumpCnt = 0;
		while (!q.isEmpty()) {
			jumpCnt++;
			for (int size = q.size(); size > 0; size--) {
				int [] cur = q.poll();
				chainQ = new ArrayDeque<>();
				if (isKilled(cur[0], cur[1], thiefY, thiefX)) return jumpCnt;

				while (!chainQ.isEmpty()) {
					int [] chainBomb = chainQ.poll();
					if (isKilled(chainBomb[0], chainBomb[1], thiefY, thiefX)) return jumpCnt;
				}
			}
		}
		return jumpCnt;
	}

	static boolean isKilled(int y, int x, int thiefY, int thiefX) {
		for (int d = 0; d < 4; d++) {
			int nextY = y, nextX = x;
			while (true) {
				nextY += delta[d][0]; nextX += delta[d][1];
				if (isOut(nextY, nextX) || visited[nextY][nextX]) break;
				visited[nextY][nextX] = true;
				if (nextY == thiefY && nextX == thiefX) return true;
				if (classroom[nextY][nextX] == '1') {
					q.offer(new int[] {nextY, nextX});
					break;
				}
				chainQ.offer(new int[] {nextY, nextX});
			}
		}
		return false;
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
