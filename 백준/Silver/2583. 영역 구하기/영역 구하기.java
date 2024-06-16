import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int M, N, K;
	static boolean [][] area;
	static List<Integer> areaSize;
	public static void main(String[] args) throws IOException{		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		area = new boolean[M][N];
		areaSize = new ArrayList<>();
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int startX = Integer.parseInt(st.nextToken());
			int startY = M-1 - Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());
			int endY = M-1 - Integer.parseInt(st.nextToken());
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
		for(int r = startY; r > endY; r--) {
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
}