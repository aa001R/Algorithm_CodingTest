import java.io.*;
import java.util.*;

public class Main {
	static int M, N, K;
	static boolean [][] area;
	static List<Integer> areaSize;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		M = read();
		N = read();
		K = read();
		area = new boolean[M][N];
		areaSize = new ArrayList<>();
		for(int k = 0; k < K; k++) {
			int startX = read();
			int startY = read();
			int endX = read();
			int endY = read();
			drawRectangle(startX, startY, endX, endY);
		}
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(area[i][j]) continue;
				bfs(i, j);
			}
		}
		Collections.sort(areaSize);
		bw.append(Integer.toString(areaSize.size())); bw.newLine();
		for(int i = 0; i < areaSize.size(); i++) {
			bw.append(Integer.toString(areaSize.get(i))).append(" ");
		}
		bw.flush();
	}

	private static void drawRectangle(int startX, int startY, int endX, int endY) {
		for(int r = startY; r < endY; r++) {
			for(int c = startX; c < endX; c++) {
				area[r][c] = true;
			}
		}
	}

	private static void bfs(int i, int j) {
		int [][] delta = {{0, 1},{0, -1},{1, 0},{-1, 0}};
		ArrayDeque<int []> que = new ArrayDeque<>();
		que.offer(new int[] {i, j});
		area[i][j] = true;
		int size = 0;
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			size++;
			for(int d = 0; d < 4; d++) {
				int r = cur[0] + delta[d][0];
				int c = cur[1] + delta[d][1];
				if(isOut(r, c)) continue;
				if(area[r][c]) continue;
				area[r][c] = true;
				que.offer(new int[] {r,c});
			}
		}
		areaSize.add(size);
	}

	private static boolean isOut(int r, int c) {
		return (r < 0 || r >= M || c < 0 || c >= N);
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
