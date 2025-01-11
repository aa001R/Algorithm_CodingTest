import java.io.*;
import java.util.*;

public class Main {
	final static int MOD = 1000;
	public static int N;
	public static int[][] origin;	// A^1 일 때의 배열(초기 배열)
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = (int) read();
		long B = read();
		origin = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				origin[i][j] = (int) read() % MOD;
			}
		}
		int [][] result = pow(origin, B);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bw.append(Integer.toString(result[i][j])).append(" ");
			}
			bw.newLine();
		}
		bw.flush();
	}

	static int [][] pow(int[][] A, long exp) {
		if (exp == 1L) { return A; } // 지수가 1일 땐 A를 return한다.
		int [][] ret = pow(A, exp / 2); // 지수를 절반으로 분할하여 재귀호출
		ret = multiply(ret, ret); // 하위 재귀에서 얻은 행렬을 제곱해준다.
		if (exp % 2 == 1) { ret = multiply(ret, origin); }
		return ret;
	}

	// o1과 o2 행렬을 곱해주는 메소드
	public static int[][] multiply(int[][] o1, int[][] o2) {
		int[][] ret = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					ret[i][j] += o1[i][k] * o2[k][j]; // 행렬곱
					ret[i][j] %= MOD;
				}
			}
		}
		return ret;
	}

	static long read() throws Exception{
		long n = System.in.read() & 15, cur;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
