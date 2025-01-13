import java.io.*;

public class Main {
	static boolean[][] voteArea;
	static int[] population;
	static int totalPopulation = 0;
	static int N;
	static int minDiff = 1001;
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read();
		population = new int[N+1];
		voteArea = new boolean[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			population[i] = read();
			totalPopulation += population[i];
		}
		for(int i = 1; i <= N; i++) {
			int adjCnt = read();
			for(int j = 1; j <= adjCnt; j++) {
				int adjoin = read();
				voteArea[i][adjoin] = true; voteArea[adjoin][i] = true;
			}
		}

		chooseArea(0, new boolean[N+1]);

		if(minDiff == 1001) bw.append("-1");
		else bw.append(Integer.toString(minDiff));
		bw.flush();
	}

	static void chooseArea(int cnt, boolean [] group) {
		if(cnt == N) {
			int aCnt = 0, bCnt = 0;
			for(int i = 1; i <= N; i++) {
				if (group[i]) aCnt++;
				else bCnt++;
			}
			if(bCnt == 0 || aCnt == 0) return;

			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(group[i] == group[j] && !connect(i, j, new boolean[N+1], group)) return;
				}
			}

			int sum = 0;
			for(int i = 1; i <= N; i++) {
				if (group[i]) sum += population[i];
			}
			minDiff = Math.min(minDiff, Math.abs(totalPopulation - sum * 2));
			return;
		}
		group[cnt] = true;
		chooseArea(cnt+1, group);
		group[cnt] = false;
		chooseArea(cnt+1, group);
	}

	static boolean connect(int curr, int c, boolean [] visited, boolean [] group) {
		boolean connection = false;
		visited[curr] = true;
		if(curr == c) {
			return connection = true;
		}
		for(int i = 1; i <= N && !connection; i++) {
			if(voteArea[curr][i] && !visited[i] && group[curr] == group[i]) {
				connection = connect(i, c, visited, group);
			}
		}
		return connection;
	}

	static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
