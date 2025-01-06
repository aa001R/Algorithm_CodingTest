import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static char [][] burnt;
	static ArrayDeque<int []> fireQ = new ArrayDeque<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		R = read(); C = read();
		burnt = new char[R][C];
		int [] startJi = new int [2];
		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				char cur = input.charAt(j);
				if (cur == 'J') {
					startJi = new int[] {i, j};
				} else if (cur == 'F') {
					fireQ.offer(new int[] {i, j});
				}
				burnt[i][j] = cur;
			}
		}

		int escapeTime = bfs(startJi[0], startJi[1]);
		if (escapeTime == 0){
			bw.write("IMPOSSIBLE");
		} else {
			bw.write(Integer.toString(escapeTime));
		}
		bw.flush();
	}

	static int bfs(int srJ, int scJ) {
		ArrayDeque<int []> jihoonQ = new ArrayDeque<>();
		boolean [][] visited = new boolean[R][C];
		jihoonQ.offer(new int[] {srJ, scJ});
		visited[srJ][scJ] = true;

		int escapeTime = 0;
		int [][] delta = {{1, 0},{0, 1},{-1, 0},{0, -1}};
		while(!jihoonQ.isEmpty()){
			for (int size = fireQ.size(); size > 0; size--) {
				int [] fire = fireQ.poll();
				for (int d = 0; d < 4; d++) {
					int nrF = fire[0] + delta[d][0];
					int ncF = fire[1] + delta[d][1];
					if (isOut(nrF, ncF) || burnt[nrF][ncF] != '.') continue;
					burnt[nrF][ncF] = 'F';
					fireQ.offer(new int[] {nrF, ncF});
				}
			}
			escapeTime++;
			for (int size = jihoonQ.size(); size > 0; size--) {
				int [] jihoon = jihoonQ.poll();
				for (int d = 0; d < 4; d++) {
					int nrJ = jihoon[0] + delta[d][0];
					int ncJ = jihoon[1] + delta[d][1];
					if (isOut(nrJ, ncJ)) return escapeTime;
					if (burnt[nrJ][ncJ] != '.' || visited[nrJ][ncJ]) continue;
					visited[nrJ][ncJ] = true;
					jihoonQ.offer(new int[] {nrJ, ncJ});
				}
			}
		}
		return 0;
	}

	static boolean isOut(int nr, int nc) {
		return (nr < 0 || nc < 0 || nr >= R || nc >= C);
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
