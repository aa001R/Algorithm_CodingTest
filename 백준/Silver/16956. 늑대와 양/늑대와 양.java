import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static char[][] area;
	static int R, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		Queue<int[]> que = new ArrayDeque();
		boolean[][] visited = new boolean[R][C];
		area = new char[R][C];
		for (int r = 0; r < R; r++) {
			String input = br.readLine();
			for (int c = 0; c < C; c++) {
				area[r][c] = input.charAt(c);
				if (area[r][c] == 'W') {
					que.offer(new int[] { r, c });
					visited[r][c] = true;
				}
			}
		}
		int[][] delta = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
		boolean dead = false;
		while (!que.isEmpty() && !dead) {
			int[] curr = que.poll();
			for (int d = 0; d < 4 && !dead; d++) {
				int r = curr[0] + delta[d][0];
				int c = curr[1] + delta[d][1];
				if (r >= 0 && r < R && c >= 0 && c < C && !visited[r][c]) {
					if (area[r][c] == 'S') {
						if (area[curr[0]][curr[1]] == '.') {
							area[curr[0]][curr[1]] = 'D';
							break;
						} else if (area[curr[0]][curr[1]] == 'W') {
							dead = true;
							break;
						}
					}
					visited[r][c] = true;
					que.offer(new int[] {r, c});
				}
			}
		}
		
		
		if(dead) {
			bw.append("0");
		}
		else {
			bw.append("1");
			bw.newLine();
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					bw.append(Character.toString(area[i][j]));
				}
				bw.newLine();
			}
		}

		bw.flush();
	}

}
