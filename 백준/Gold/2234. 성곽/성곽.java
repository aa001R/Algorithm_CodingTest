import java.io.*;
import java.util.*;

public class Main {
	static int N, M, walls[][], maxRemovedWallRoomArea = 0;
	static ArrayList<Integer> roomAreas;
	static int [][] roomNum;
	static int [][] delta = {{0, -1},{-1, 0},{0, 1},{1, 0}};
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read(); M = read();
		roomNum = new int [M][N];
		walls = new int [M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				walls[i][j] = read();
			}
		}
		roomAreas = new ArrayList<>(); roomAreas.add(0);

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (roomNum[i][j] != 0) continue;
				roomAreas.add(countRoomBFS(i, j, roomAreas.size()));
			}
		}
		int visitedRoomNum = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (roomNum[i][j] <= visitedRoomNum) continue;
				countRemovedWallRoomDFS(i, j, ++visitedRoomNum, false, new boolean [M][N]);
			}
		}

		bw.append(Integer.toString(roomAreas.size() - 1)).append('\n')
			.append(Integer.toString(Collections.max(roomAreas))).append('\n')
			.append(Integer.toString(maxRemovedWallRoomArea)).append("\n");
		bw.flush();
	}

	static int countRoomBFS(int sr, int sc, int curNum){
		ArrayDeque<int []> que = new ArrayDeque<>();
		que.offer(new int [] {sr, sc});
		roomNum[sr][sc] = curNum;
		int area = 0;

		while(!que.isEmpty()){
			int [] cur = que.poll();
			for (int d = 0; d < 4; d++) {
				if ((walls[cur[0]][cur[1]] & (1 << d)) != 0) continue;
				int nr = cur[0] + delta[d][0];
				int nc = cur[1] + delta[d][1];
				if (isOut(nr, nc) || roomNum[nr][nc] != 0) continue;
				roomNum[nr][nc] = curNum;
				que.offer(new int [] {nr, nc});
			}
			area++;
		}
		return area;
	}

	static void countRemovedWallRoomDFS(int r, int c, int startRoomNum, boolean isRemovedWall, boolean[][] visited){
		if (isRemovedWall && roomNum[r][c] == startRoomNum) {
			return;
		}
		if (roomNum[r][c] < startRoomNum) return;
		if (roomNum[r][c] > startRoomNum) {
			int curArea = roomAreas.get(startRoomNum) + roomAreas.get(roomNum[r][c]);
			maxRemovedWallRoomArea = Math.max(maxRemovedWallRoomArea, curArea);
			return;
		}
		visited[r][c] = true;
		for (int d = 0; d < 4; d++) {
			if ((walls[r][c] & (1 << d)) != 0 && isRemovedWall) continue;
			int nr = r + delta[d][0];
			int nc = c + delta[d][1];
			if (isOut(nr, nc)) continue;
			if (visited[nr][nc]) continue;
			if ((walls[r][c] & (1 << d)) == 0) {
				countRemovedWallRoomDFS(nr, nc, startRoomNum, isRemovedWall, visited);
			} else {
				countRemovedWallRoomDFS(nr, nc, startRoomNum, true, visited);
			}
		}
	}

	static boolean isOut(int nr, int nc){
		return nr < 0 || nc < 0 || nr >= M || nc >= N;
	}

	static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
