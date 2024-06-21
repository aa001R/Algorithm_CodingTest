import java.util.*;
import java.io.*;

class Main {
	static ArrayDeque<int[]> q = new ArrayDeque<>();
	static int N, M;
	static int [][] iceberg, delta = {{-1, 0},{1, 0},{0, 1},{0, -1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		iceberg = new int[N][M];
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m = 0; m < M; m++) {
				iceberg[n][m] = Integer.parseInt(st.nextToken());
				if(iceberg[n][m] == 0) continue;
				q.offer(new int[] {n, m});
			}
		}
		bw.append(Integer.toString(melting()));
		bw.flush();
	}
	private static int melting() {
		int minDay = 0;
		while(!q.isEmpty()) {
			if(checkTwoIceberg()) return minDay;
			ArrayList<int []> melt = new ArrayList<>();
			for(int size = q.size(); size > 0; size--) {
				int [] cur = q.poll();
				int ice = iceberg[cur[0]][cur[1]];
				for(int d = 0; d < 4; d++) {
					int nr = cur[0] + delta[d][0];
					int nc = cur[1] + delta[d][1];
					if(isOut(nr,nc)) continue;
					if(iceberg[nr][nc] > 0) continue;
					ice--;
				}
				if(ice <= 0) {
					melt.add(new int[] {cur[0], cur[1]});
					continue;
				}
				q.offer(cur);
				iceberg[cur[0]][cur[1]] = ice;
			}
			for(int [] p: melt) {
				iceberg[p[0]][p[1]] = 0;
			}
			minDay++;
		}
		return 0;
	}
	private static boolean checkTwoIceberg() {
		int icebergCnt = 0;
		boolean [][] visited = new boolean [N][M];
		for(int n = 0; n < N; n++) {
			for(int m = 0; m < M; m++) {
				if(iceberg[n][m] == 0) continue;
				if(visited[n][m]) continue;
				icebergCnt++;
				checkBfs(n, m, visited);
			}
		}
		if(icebergCnt >= 2) return true;
		return false;
	}
	
	private static void checkBfs(int sr, int sc, boolean[][] visited) {
		ArrayDeque<int []> que = new ArrayDeque<>();
		que.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		while(!que.isEmpty()) {
			int [] cur = que.poll();
			for(int d=0; d<4; d++) {
				int nr = cur[0]+delta[d][0];
				int nc = cur[1]+delta[d][1];
				if(isOut(nr,nc)) continue;
				if(visited[nr][nc]) continue; visited[nr][nc] = true;
				if(iceberg[nr][nc] == 0) continue;
				que.offer(new int[] {nr, nc});
			}
		}
	}
	private static boolean isOut(int nr, int nc) {
		return (nr < 0 || nr >= N || nc < 0 || nc >= M);
	}
}