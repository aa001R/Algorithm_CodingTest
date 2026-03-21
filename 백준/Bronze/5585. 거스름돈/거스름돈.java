import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int [] change = {500,100,50,10,5,1};
		int money = 1000 - read();
		int count = 0;
		for (int i = 0; i < change.length && money > 0; i++) {
			count += money / change[i];
			money %= change[i];
		}
		bw.append(String.valueOf(count));
		bw.flush();
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if(isNegative) n = System.in.read() & 15;
		while((cur = System.in.read()) > 32) n = (n << 3) + (n << 1) + (cur & 15);
		return isNegative? -n : n;
	}
}
