import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = read();
		int m = read();
		int leafCnt = 0;
		if(m == 2) {
			leafCnt = 1;
		}
		int lastLeaf = 0;
		for(int i = 1; i < n; i++) {
			if(m>leafCnt) {
				bw.append(Integer.toString(0)).append(" ").append(Integer.toString(i));
				leafCnt++;
			}else {
				bw.append(Integer.toString(lastLeaf)).append(" ").append(Integer.toString(i));
			}
			bw.newLine();
			lastLeaf = i;
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