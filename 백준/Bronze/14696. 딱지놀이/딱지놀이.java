import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int gameCnt = read();
		int ttakjiCnt = 0;
		for(int i = 0; i < gameCnt; i++) {
			int [] A = new int[5];
			ttakjiCnt = read();
			for(int t = 0; t < ttakjiCnt; t++) {
				A[read()]++;
			}
			int [] B = new int[5];
			ttakjiCnt = read();
			for(int t = 0; t < ttakjiCnt; t++) {
				B[read()]++;
			}
			boolean draw = true;
			for(int ttakji = 4; ttakji >= 1; ttakji--) {
				if(A[ttakji] == B[ttakji]) continue;
				draw = false;
				if(A[ttakji] > B[ttakji]) bw.append("A\n");
				else bw.append("B\n");
				break;
			}
			
			if(draw) bw.append("D\n");
		}
		bw.flush();
	}
	static public int read() throws Exception {
		int n = 0, cur;
		boolean isNumber = false;
		while(true) {
			cur = System.in.read();
			if(cur <= 32) {
				if (isNumber) return n;
			}else {
				isNumber = true;
				n = (n<<3)+(n<<1)+(cur&15);
			}
		}
	}
}