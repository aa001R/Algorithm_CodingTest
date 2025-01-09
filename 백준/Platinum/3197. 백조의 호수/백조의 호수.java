import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static int[][] days;
	static int[][] person;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		days = new int[R][C];
		person = new int[2][2];
		int l = 0;
		Queue<int[]> que = new ArrayDeque<>();
		for(int r = 0; r < R; r++) {
			String input = br.readLine();
			for(int c = 0; c < C; c++) {
				if(input.charAt(c) != 'X') {
					que.offer(new int[] {r, c});
					days[r][c] = 1;
					if(input.charAt(c) == 'L') {
						person[l][0] = r;
						person[l++][1] = c;
					}
				}
			}
		}

		// 얼음이 녹는 날 bfs로 기록
		int [][] delta = {{0, -1},{-1, 0},{0, 1},{1, 0}};
		int maxDay = 0;
		while(!que.isEmpty()) {
			int [] curr = que.poll();
			for(int d = 0; d < 4; d++) {
				int r = curr[0] + delta[d][0];
				int c = curr[1] + delta[d][1];
				if(r >= 0 && r < R && c >= 0 && c < C && days[r][c] == 0 ) {
					days[r][c] = days[curr[0]][curr[1]] + 1;
					que.offer(new int[] {r, c});
					if(maxDay < days[r][c] - 1) maxDay = days[r][c] - 1;
				}
			}
		}

		// 둘이 만나는 최소 날 binarySearch로 확인
		bw.write(Integer.toString(binarySearch(0, maxDay)));
		bw.flush();
	}
	// lower
	static int binarySearch(int start, int last) {
		while(start < last) {
			int mid = (start + last) / 2;
			if(meet(mid)) last  = mid;
			else start = mid + 1;
		}
		return start;
	}

	static boolean meet(int day) {
		Queue<int[]> que = new ArrayDeque<>();
		int [][] delta = {{0, -1},{-1, 0},{0, 1},{1, 0}};
		boolean [][] visited = new boolean[R][C];

		que.offer(new int[] {person[0][0], person[0][1]});
		visited[person[0][0]][person[0][1]] = true;

		while(!que.isEmpty()) {
			int [] curr = que.poll();
			if(curr[0] == person[1][0] && curr[1] == person[1][1]) {
				return true;
			}
			for(int d = 0; d < 4; d++) {
				int r = curr[0] + delta[d][0];
				int c = curr[1] + delta[d][1];
				if(r >= 0 && r < R && c >= 0 && c < C && !visited[r][c] && days[r][c] - 1 <= day ) {
					visited[r][c] = true;
					que.offer(new int[] {r, c});
				}
			}
		}
		return false;
	}

}
