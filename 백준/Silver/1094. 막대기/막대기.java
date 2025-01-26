import java.io.*;

public class Main {
	static int X;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		X = read();
		int stickLen = 0, cnt = 0;
		for (int i = 6; i >= 0; i--) {
			if ((stickLen + (1 << i)) > X) { continue; }
			cnt++;
			stickLen += 1 << i;
			if (stickLen == X) { break; }
		}
		bw.write(Integer.toString(cnt));
		bw.flush();
	}

	static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
