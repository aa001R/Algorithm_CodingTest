import java.io.*;
import java.util.Arrays;

public class Main {
	static int [] chessCnt = {1, 1, 2, 2, 2, 8};
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < chessCnt.length; i++) {
			chessCnt[i] -= read();
			bw.append(Integer.toString(chessCnt[i])).append(" ");
		}
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
