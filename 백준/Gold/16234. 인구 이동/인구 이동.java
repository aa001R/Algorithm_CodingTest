import java.io.*;
import java.util.*;

public class Main {
	static int N, L, R;
	static int[][] nation;
	static int[] parent, sum, cnt;
	static boolean move;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		nation = new int[N][N];
		parent = new int[N*N];
		sum = new int[N*N];
		cnt = new int[N*N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				nation[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int day = 0;
		do {
			move = false;
			makeSet();
			boolean[][] visited = new boolean[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (visited[r][c]) continue;
					bfs(r, c, visited);
				}
			}
			if (!move) {break;}
			movePeople(visited);
			day++;
		}while(move);
		
		bw.write(Integer.toString(day));
		bw.flush();
	}
	
	static void makeSet() {
		for(int i = 0; i < N*N; i++) {
			parent[i] = i;
		}
	}
	
	static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA == rootB) return false;
		parent[rootB] = rootA;
		return true;
	}

	static void bfs(int r, int c, boolean[][] visited) {
		Queue<int[]> que = new ArrayDeque<>();
		visited[r][c] = true;
		int nodeNum = r*N+c;
		sum[nodeNum] = nation[r][c];
		cnt[nodeNum] = 1;
		que.offer(new int[] { r, c });
		
		int[][] delta = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		while (!que.isEmpty()) {
			int[] curr = que.poll();
			for (int d = 0; d < 4; d++) {
				int nr = curr[0] + delta[d][0];
				int nc = curr[1] + delta[d][1];
				int next = nr*N+nc;
				if (!isIn(nr, nc)) continue;
				if (visited[nr][nc]) continue;
				int diff = Math.abs(nation[curr[0]][curr[1]] - nation[nr][nc]);
				if (diff < L || diff > R) continue;
				que.offer(new int[] { nr, nc });
				visited[nr][nc] = true;
				union(nodeNum, next);
				sum[nodeNum] += nation[nr][nc];
				cnt[nodeNum]++;
				move = true;
			}
		}
	}

	static void movePeople(boolean [][] visited) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c]) continue;
				nation[r][c] = sum[find(r*N+c)] / cnt[find(r*N+c)];
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	}
	
}