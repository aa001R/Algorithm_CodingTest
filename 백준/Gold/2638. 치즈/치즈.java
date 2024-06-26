import java.util.*;
import java.io.*;

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M, cheeseCnt, cheese[][], delta[][] = {{-1, 0},{0, -1},{1, 0},{0, 1}};;
	static boolean [][] air;
	static ArrayDeque <int []> airQue = new ArrayDeque<>();
	public static void main(String[] args) throws IOException
	{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheese = new int[N][M];
		air = new boolean[N][M];
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m = 0; m < M; m++) {
				cheese[n][m] = Integer.parseInt(st.nextToken());
				if(cheese[n][m] == 1) cheeseCnt++;
			}
		}
		inputAirPos(0, 0);
		bw.append(Integer.toString(AllmeltingDay()));
		bw.flush();
	}
	private static void inputAirPos(int sr, int sc) {
		ArrayDeque<int []> q = new ArrayDeque<>();
		q.offer(new int[] {sr,sc});
		air[sr][sc] = true;
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			boolean nearCheese = false;
			for(int d= 0; d < 4; d++) {
				int nr = cur[0] + delta[d][0];
				int nc = cur[1] + delta[d][1];
				if(isOut(nr, nc)) continue;
				if(air[nr][nc]) continue;
				if(cheese[nr][nc] == 0) {
					air[nr][nc] = true;
					q.offer(new int[] {nr, nc});
					continue;
				}
				nearCheese = true;
			}
			if(nearCheese) airQue.offer(cur);
		}
	}
	private static int AllmeltingDay() {
		int hour = 0;
		while(cheeseCnt > 0) {
			for(int size = airQue.size(); size > 0; size--) {
				int [] cur = airQue.poll();
				for(int d= 0; d < 4; d++) {
					int nr = cur[0] + delta[d][0];
					int nc = cur[1] + delta[d][1];
					if(isOut(nr, nc)) continue;
					if(air[nr][nc]) continue;
					if(cheese[nr][nc] != 0 && ++cheese[nr][nc] > 2) {
						--cheeseCnt;
						air[nr][nc] = true;
						inputAirPos(nr, nc);
					}
				}
			}
			hour++;
		}
		return hour;
	}
	private static boolean isOut(int nr, int nc) {
		return nr < 0 || nr >= N || nc < 0 || nc >= M;
	}
}