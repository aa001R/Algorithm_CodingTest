import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static char[][] image;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = read();
		image = new char[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				image[i][j] = line.charAt(j);
			}
		}

		quad(image, 0, 0, N);
		System.out.println(sb);
	}

	static void quad(char[][] image, int x, int y, int size) {
		if (IsPossible(image, x, y, size, image[x][y])) {
			sb.append(image[x][y]);
			return;
		}
		sb.append("(");
		quad(image, x, y, size / 2);
		quad(image, x, y + size / 2, size / 2);
		quad(image, x + size / 2, y, size / 2);
		quad(image, x + size / 2, y + size / 2, size / 2);
		sb.append(")");
	}

	static boolean IsPossible(char[][] image, int x, int y, int size, int val) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (image[i][j] != val) return false;
			}
		}
		return true;
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
