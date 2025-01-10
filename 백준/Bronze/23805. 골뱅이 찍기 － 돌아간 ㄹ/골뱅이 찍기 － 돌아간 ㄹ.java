import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = read();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 3; k++) {
					bw.append("@");
				}
			}
			for (int j = 0; j < N; j++) {
				bw.append(" ");
			}
			for (int j = 0; j < N; j++) {
				bw.append("@");
			}
			bw.newLine();
		}
		for (int k = 0; k < 3; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bw.append("@");
				}
				for (int j = 0; j < N; j++) {
					bw.append(" ");
				}
				for (int j = 0; j < N; j++) {
					bw.append("@");
				}
				for (int j = 0; j < N; j++) {
					bw.append(" ");
				}
				for (int j = 0; j < N; j++) {
					bw.append("@");
				}
				bw.newLine();
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bw.append("@");
			}
			for (int j = 0; j < N; j++) {
				bw.append(" ");
			}
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 3; k++) {
					bw.append("@");
				}
			}
			bw.newLine();
		}
		bw.flush();
	}

	static int read() throws Exception{
		int n = System.in.read() & 15, cur;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
