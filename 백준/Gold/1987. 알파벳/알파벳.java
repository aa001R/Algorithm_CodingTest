import java.util.*;
import java.io.*;

public class Main {
	static int max;
	static int[][] board;
	static boolean [] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		board = new int[read()+2][read()+2];
		visited = new boolean[27];

		for(int i = 1; i < board[0].length-1; i++) {
			board[0][i] = board[board.length-1][i] = 91;
		}
		for(int i = 0; i < board.length; i++) {
			board[i][0] = board[i][board[i].length-1] = 91;
		}
		visited[26] = true;

		for(int i = 1; i < board.length-1; i++)
			for(int j = 1; j < board[i].length-1; j++)
				board[i][j] = nextChar();


		max = 1;
		visited[board[1][1] - 65] = true;
		dfs(1, 1, 1);
		bw.append(Integer.toString(max));
		bw.flush();
	}

	static int [][]delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static void dfs(int r, int c, int cnt) {
		if(visited[board[r+1][c] - 65] && visited[board[r][c+1] - 65]
		&& visited[board[r-1][c] - 65] && visited[board[r][c-1] - 65]) {
			max = Math.max(max, cnt);
			return;
		}
		for(int d = 0; d < 4; d++) {
			int nr = delta[d][0] + r;
			int nc = delta[d][1] + c;
			if (visited[board[nr][nc] - 65]) continue;
			visited[board[nr][nc] - 65] = true;
			dfs(nr, nc, cnt + 1);
			visited[board[nr][nc] - 65] = false;
		}
	}

	static char nextChar() throws IOException{
		int n;
		while((n = System.in.read())<=' ');
		return (char)n;
	}

	static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
