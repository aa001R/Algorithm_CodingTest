import java.util.*;
import java.io.*;

public class Main {
	static int N, min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		int maxR = -1, maxC = -1, minR = N, minC = N;
		for (int r = 0; r < N; r++) {
			String input = br.readLine();
			for (int c = 0; c < N; c++){
				if (input.charAt(c) == '.') continue;
				if (maxR < r) maxR = r;
				if (minR > r) minR = r;
				if (maxC < c) maxC = c;
				if (minC > c) minC = c;
			}
		}
		int moveLeftOrRight = minC == maxC ? 0 : Math.min(N - 1 - minC, maxC);
		int moveTopOrBottom = maxR == minR ? 0 : Math.min(N - 1 - minR, maxR);
		System.out.println(moveLeftOrRight + moveTopOrBottom);
	}
}
