import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = read();
		for(int test = 0; test < T; test++) {
			int N = read();
			int K = read();
			long count = binarySearch(0, 5000, N, K);
			long diff = N-1 - (count+1)*count*K/2;
			
			if(count%2 == 0) {
				long num = -1 * K * (count/2) - diff;
				bw.append(Long.toString(num)).append(" ").append("L\n");
			} else {
				long num = K * (count/2+1) + diff;
				bw.append(Long.toString(num)).append(" ").append("R\n");
			}
		}
		bw.flush();
	}
	
	private static long binarySearch(long start, long end, int n, int k) {
		if(start >= end)
			return start;
		long middle = (start+end)/2;
		
		if((middle+1)*middle*k/2 >= n)
			return binarySearch(start, middle, n, k);
		else
			return binarySearch(middle+1, end, n, k);
	}
	
	static public int read() throws Exception {
		int n = 0, cur;
		boolean isNumber = false;
		while(true) {
			cur = System.in.read();
			if(cur <= 32) {
				if(isNumber) return n;
			}else {
				isNumber = true;
				n = (n << 3) + (n << 1) + (cur & 15);
			}
		}
	}
}