import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int n = read();
		// pos[v] = 값 v가 원래 배열에서 몇 번째 위치인지 (1-indexed)
		int[] pos = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			pos[read()] = i;
		}

		// pos[i-1] < pos[i] 이면 연속 구간 유지, 아니면 리셋
		int maxKeep = 1;
		int curLen = 1;
		for (int i = 2; i <= n; i++) {
			if (pos[i] > pos[i - 1]) {
				curLen++;
				maxKeep = Math.max(maxKeep, curLen);
			} else {
				curLen = 1;
			}
		}
		System.out.println(n - maxKeep);
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if(isNegative) n = System.in.read() & 15;
		while((cur = System.in.read()) > 32) n = (n << 3) + (n << 1) + (cur & 15);
		return isNegative? -n : n;
	}

}
