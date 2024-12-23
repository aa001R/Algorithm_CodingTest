import java.io.*;
import java.util.*;

public class Main {
	static int N, M, J;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read();
		M = read();
		J = read();
		int curP = M;
		int cnt = 0;
		for (int i = 0; i < J; i++) {
			int appleP = read();
			if (curP - M + 1 <= appleP && appleP <= curP ) continue;
			else if (curP - M + 1 > appleP) {
				cnt += (curP - M + 1 - appleP);
				curP = appleP + M - 1;
			} else {
				cnt += (appleP - curP);
				curP = appleP;
			}
		}
		bw.append(Integer.toString(cnt));
		bw.flush();
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
