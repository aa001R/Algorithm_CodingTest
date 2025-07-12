import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int N = read();
		long [][] boardDp = new long[N][N];
		boardDp[0][0] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int jump = read();
				if (jump == 0) {continue;}
				if (j + jump < N) {
					boardDp[i][j + jump] += boardDp[i][j];
				}
				if (i + jump < N) {
					boardDp[i + jump][j] += boardDp[i][j];
				}
			}
		}
		System.out.println(boardDp[N-1][N-1]);
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		while((cur = System.in.read()) > 32){
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
