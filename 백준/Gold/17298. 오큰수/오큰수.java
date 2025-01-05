import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = read();
		int [] nge = new int[N];
		ArrayDeque <int []> stack = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			int num = read();
			if (stack.isEmpty()) {
				stack.push(new int[] {i, num});
				continue;
			}
			while(!stack.isEmpty() && stack.peek()[1] < num){
				nge[stack.pop()[0]] = num;
			}
			stack.push(new int[] {i, num});
		}
		while(!stack.isEmpty()){
			nge[stack.pop()[0]] = -1;
		}
		for (int num : nge) {
			bw.append(Integer.toString(num)).append(" ");
		}
		bw.flush();
	}

	static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) { n = System.in.read() & 15; }
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? -n : n;
	}
}
