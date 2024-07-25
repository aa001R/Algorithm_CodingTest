import java.io.*;
 
import java.util.*;
 
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test = read();
		for(int i = 0; i < test; i++) {
			int min = 101, sum = 0;
			for(int n = 0; n < 7; n++) {
				int num = read();
				if(num % 2 != 0) continue;
				sum += num;
				min = Math.min(min, num);
			}
			bw.append(Integer.toString(sum)).append(" ").append(Integer.toString(min)); bw.newLine();
		}
		bw.flush();
	}
	
	public static int read() throws Exception {
		int n = 0, cur;
		boolean isNumber = false;
		while(true) {
			cur = System.in.read();
			if(cur <= 32) {
				if(isNumber) return n;
			} else {
				isNumber = true;
				n = (n << 3) + (n << 1) + (cur & 15); 
			}
		}
	}
}