import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		int A = read();
		int B = read();
		int C = read();
		int [] time = new int [101];
		for (int i = 0; i < 3; i++) {
			int startT = read();
			int endT = read();
			for (int j = startT; j < endT; j++) {
				time[j]++;
			}
		}

		int cost = 0;
		for (int t : time) {
			switch (t){
				case 1 : cost += A; break;
				case 2 : cost += (B*2); break;
				case 3 : cost += (C*3); break;
			}
		}
		System.out.println(cost);
	}

	public static int read() throws IOException {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) { n = System.in.read() & 15; }
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? -n : n;
	}
}
