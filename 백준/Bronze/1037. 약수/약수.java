import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int divisorCnt = read();
		ArrayList <Integer> divisor = new ArrayList<>();
		for(int i = 0; i < divisorCnt; i++) {
			divisor.add(read());
		}
		Collections.sort(divisor);
		if(divisorCnt % 2 == 0) {
			bw.append(Integer.toString(divisor.get(0) * divisor.get(divisorCnt-1)));
		}else {
			bw.append(Integer.toString(divisor.get(divisorCnt/2)*divisor.get(divisorCnt/2)));
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