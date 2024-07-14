import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = read();
		for(int test = 0; test < T; test++) {
			int N = read();
			int K = read();
			int dir = 1;
			int posX = 0;
			int totalLen = 0;
			for(int i = 1; totalLen < N; totalLen+= (K*i), i++) {
				if( totalLen+(K*i) < N) { 
					posX += (K*i)*dir;
					dir *= -1;
				}
				else {
					posX += (N-1-totalLen)*dir;
				}
			}
			bw.append(Integer.toString(posX)).append(" ").append(dir==1?"R":"L");
			bw.newLine();
		}
		bw.flush();
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