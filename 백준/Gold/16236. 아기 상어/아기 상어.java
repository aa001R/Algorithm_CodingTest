import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] board;
	static int [][] delta = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	static int [] shark = new int [2];
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read();
		board = new int[N][N];
		int fishCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = read();
				if (board[i][j] > 0 && board[i][j] < 9) fishCnt++;
				else if (board[i][j] == 9) {
					shark[0] = i; shark[1] = j;
					board[i][j] = 0;
				}
			}
		}

		bw.write(Integer.toString(calculateTime(fishCnt)));
		bw.flush();
	}

	private static int calculateTime(int fishCount) {
		int sharkSize = 2, totalTime = 0, eatenFish = 0;
		while (fishCount > 0) {
			int distance = findClosestFish(sharkSize);
			if (distance == 0) break;

			totalTime += distance;
			fishCount--;
			eatenFish++;

			if (eatenFish == sharkSize) {
				sharkSize++;
				eatenFish = 0;
			}
		}
		return totalTime;
	}

	private static int findClosestFish(int sharkSize) {
		boolean[][] visited = new boolean[N][N];
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { shark[0], shark[1] });
		visited[shark[0]][shark[1]] = true;

		int distance = 0;
		int targetY = -1, targetX = -1;

		while (!queue.isEmpty()) {
			boolean isEatFish = false;

			for (int size = queue.size(); size > 0; size--) {
				int[] cur = queue.poll();

				for (int d = 0; d < 4; d++) {
					int ny = cur[0] + delta[d][0], nx = cur[1] + delta[d][1];
					if (isOut(ny, nx) || visited[ny][nx] || board[ny][nx] > sharkSize) continue;
					queue.add(new int[] { ny, nx });
					visited[ny][nx] = true;

					if (board[ny][nx] == 0 || board[ny][nx] == sharkSize) continue;
					isEatFish = true; // 먹을 수 있는 생선 찾음
					// 위 > 왼쪽 우선
					if (targetY == -1 || ny < targetY || (ny == targetY && nx < targetX)) {
						targetY = ny;
						targetX = nx;
					}
				}
			}

			distance++;
			if (isEatFish) {
				board[targetY][targetX] = 0;
				shark[0] = targetY; shark[1] = targetX;
				return distance;
			}
		}
		return 0;
	}

	static boolean isOut(int ny, int nx){
		return ny < 0 || nx < 0 || nx >= N || ny >= N;
	}

	static int read() throws Exception{
		int n = System.in.read() & 15, cur;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
