import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int N, max;
	static int [][] area;
	static boolean[] check = new boolean[101];
	public static void main(String[] args) throws IOException{		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		area = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
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
		boolean [][] rainAfterArea = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(rainAfterArea[i][j] || area[i][j] <= rain ) continue;
				cnt++;
				bfs(i, j, rainAfterArea, rain);
			}
		}
		max = Math.max(max, cnt);
	}
	
	private static void bfs(int i, int j, boolean[][] rainAfterArea, int rain) {
		int [][] delta = {{0, 1},{0, -1},{1, 0},{-1, 0}};
		ArrayDeque<int []> que = new ArrayDeque<>();
		que.offer(new int[] {i, j});
		rainAfterArea[i][j] = true;
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			for(int d = 0; d < 4; d++) {
				int r = cur[0] + delta[d][0];
				int c = cur[1] + delta[d][1];
				if(isOut(r, c)) continue;
				if(rainAfterArea[r][c]) continue;
				rainAfterArea[r][c] = true;
				if(area[r][c]<=rain) continue;
				que.offer(new int[] {r,c});
			}
		}		
	}
	
	private static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
}