import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		long A = read();
		long B = read();
		long C = read();
		System.out.println(exp2(A%C, B, C));
	}

	public static long exp2(long a, long b, long c) {
		if (b == 1) return a % c;
		long y = exp2(a, b/2, c) % c;
		return (b%2 == 0) ? (y*y) % c : (((y*y)%c)*a) % c;
	}

	static long read() throws IOException {
		long n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) { n = System.in.read() & 15; }
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative? -n : n;
	}
}
