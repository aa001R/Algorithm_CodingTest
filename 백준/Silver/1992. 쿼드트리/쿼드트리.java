import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static String[] board;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read();
		board = new String[N];
		for(int i = 0; i < N; i++) {
			board[i] = br.readLine();
		}
		bw.write(div(0, 0,  N-1, N-1));
		bw.flush();
	}

	static String div(int startR, int startC, int endR, int endC) {
		if(startR == endR && startC == endC){
			return String.valueOf(board[startR].charAt(startC));
		}
		StringBuilder sb = new StringBuilder();
		int midR = (startR+endR)/2;
		int midC = (startC+endC)/2;
		String leftUp = div(startR, startC, midR, midC);
		String rightUp = div(startR, midC+1, midR, endC);
		String leftDown = div(midR+1, startC, endR, midC);
		String rightDown = div(midR+1, midC+1, endR, endC);

		if((leftUp.equals("0") && leftDown.equals("0") && rightUp.equals("0") && rightDown.equals("0")) 
		   || (leftUp.equals("1") && leftDown.equals("1") && rightUp.equals("1") && rightDown.equals("1"))) {
			sb.append(leftUp);
		}else {
			sb.append("(").append(leftUp).append(rightUp).append(leftDown).append(rightDown).append(")");
		}
		return sb.toString();
	}

	static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) {
			n = System.in.read() & 15;
		}
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? -n : n;
	}
}
