import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int stoneCnt = read();
		if(stoneCnt % 7 == 1 || stoneCnt % 7 == 3) {
			bw.append("CY\n");
		}else {
			bw.append("SK\n");
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