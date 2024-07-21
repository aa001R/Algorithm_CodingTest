import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int blogRunningDay = read();
		int during = read();
		int visitor [] = new int[blogRunningDay];
		for(int i = 0; i < blogRunningDay; i++) {
			visitor[i] = read();
		}
		int maxVisiting = 0;
		int maxVisitingCnt = 1;
		for(int i = 0; i < during; i++) {
			maxVisiting += visitor[i];
		}
		int cur = maxVisiting;
		for(int i = during; i < blogRunningDay; i++) {
			cur -= visitor[i-during];
			cur += visitor[i];
			if(maxVisiting > cur) continue;
			else if(maxVisiting == cur) maxVisitingCnt++;
			else {
				maxVisiting = cur;
				maxVisitingCnt = 1;
			}
		}
		
		if(maxVisiting == 0) {
			bw.append("SAD\n");
		}else {
			bw.append(Integer.toString(maxVisiting)).append("\n")
			.append(Integer.toString(maxVisitingCnt));
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