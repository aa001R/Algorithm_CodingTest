import java.util.*;
import java.io.*;

public class Main {
	static int N, min;
	static int [] dr = {-1, -1, 1, 1};
	static int [] dc = {-1, 1, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		char[][] board = new char[N][N];
		Set<Integer> gomRows = new HashSet<>();
		Set<Integer> gomCols = new HashSet<>();
		for (int r = 0; r < N; r++) {
			String input = br.readLine();
			for (int c = 0; c < N; c++){
				board[r][c] = input.charAt(c);
				if (board[r][c] == 'G') {
					gomRows.add(r);
					gomCols.add(c);
				}
			}
		}
		if (gomRows.size() == 1 && gomCols.size() == 1) {
			System.out.println("0");
			return;
		}
		for (int d = 0; d < 4; d++) {
			play(new HashSet<>(gomRows), new HashSet<>(gomCols), d);
		}

		System.out.println(min);
	}
	static void play(Set<Integer> gomRows, Set<Integer> gomCols, int d){
		int cnt = 0;
		while (gomRows.size() > 1 && cnt <= min) {
			gomRows = move(gomRows, dr[d]);
			cnt++;
		}

		while (gomCols.size() > 1 && cnt <= min) {
			gomCols = move(gomCols, dc[d]);
			cnt++;
		}

		min = Math.min(min, cnt);
	}

	static Set<Integer> move(Set<Integer> set, int delta) {
		Set<Integer> next = new HashSet<>();

		for (Integer v : set) {
			int nv = v + delta;
			if (isOut(nv)) {
				next.add(v);
			} else {
				next.add(nv);
			}
		}

		return next;
	}

	static boolean isOut(int p) {
		return p < 0 || p >= N;
	}
}
