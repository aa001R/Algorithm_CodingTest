import java.util.*;
import java.io.*;

public class Main {
	static int max;
	static int[][] board;
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		board = new int[read()+2][read()+2];

		for(int i = 1; i < board[0].length-1; i++) {
			board[0][i] = board[board.length-1][i] = 91;
		}
		for(int i = 0; i < board.length; i++) {
			board[i][0] = board[i][board[i].length-1] = 91;
		}

		for(int i = 1; i < board.length-1; i++)
			for(int j = 1; j < board[i].length-1; j++)
				board[i][j] = nextChar();


		max = 1;
		dfs(1, 1, 1, (1 << 26) | (1 << (board[1][1] - 65)));
		bw.append(Integer.toString(max));
		bw.flush();
	}

	static int [][]delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static void dfs(int r, int c, int cnt, int visited) {
		if( (visited & (1 << (board[r+1][c] - 65))) != 0 && (visited & (1 << (board[r-1][c] - 65))) != 0 && (visited & (1 << (board[r][c+1] - 65))) != 0 && (visited & (1 << (board[r][c-1] - 65))) != 0) {
			max = Math.max(max, cnt);
			return;
		}
		for(int d = 0; d < 4; d++) {
			int nr = delta[d][0] + r;
			int nc = delta[d][1] + c;
			if ((visited & (1 << (board[nr][nc] - 65))) != 0) continue;
			visited |= (1 << (board[nr][nc] - 65));
			dfs(nr, nc, cnt + 1, visited);
			visited &= ~(1 << (board[nr][nc] - 65));
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
