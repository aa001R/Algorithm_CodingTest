import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = read();
		bw.append(String.format("%s%s%s\n", "@".repeat(3 * N), " ".repeat(N), "@".repeat(N)).repeat(N));
		bw.append(String.format("%s%s%s%s%s\n", "@".repeat(N)," ".repeat(N),"@".repeat(N)," ".repeat(N), "@".repeat(N)).repeat(3 * N));
		bw.append(String.format("%s%s%s\n" ,"@".repeat(N), " ".repeat(N), "@".repeat(3 * N)).repeat(N));
		bw.flush();
	}

	static int read() throws Exception {
		int n = 0, cur;
		while ((cur = System.in.read()) > 32) {
			n = (n * 10) + (cur - '0');
		}
		return n;
	}
}
