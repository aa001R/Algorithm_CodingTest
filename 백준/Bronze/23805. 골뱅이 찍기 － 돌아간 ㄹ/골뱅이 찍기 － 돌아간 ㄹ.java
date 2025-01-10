import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sbtmp = new StringBuilder();
		int N = read();
		for (int j = 0; j < 3 * N; j++) {
			sbtmp.append("@");
		}
		for (int j = 0; j < N; j++) {
			sbtmp.append(" ");
		}
		for (int j = 0; j < N; j++) {
			sbtmp.append("@");
		}
		for (int i = 0; i < N; i++) {
			bw.append(sbtmp).append("\n");
		}
		sbtmp.setLength(0);

		for (int j = 0; j < N; j++) {
			sbtmp.append("@");
		}
		for (int j = 0; j < N; j++) {
			sbtmp.append(" ");
		}
		for (int j = 0; j < N; j++) {
			sbtmp.append("@");
		}
		for (int j = 0; j < N; j++) {
			sbtmp.append(" ");
		}
		for (int j = 0; j < N; j++) {
			sbtmp.append("@");
		}
		for (int k = 0; k < 3*N; k++) {
			bw.append(sbtmp).append("\n");
		}

		sbtmp.setLength(0);
		for (int j = 0; j < N; j++) {
			sbtmp.append("@");
		}
		for (int j = 0; j < N; j++) {
			sbtmp.append(" ");
		}
		for (int j = 0; j < 3 * N; j++) {
			sbtmp.append("@");
		}
		for (int i = 0; i < N; i++) {
			bw.append(sbtmp).append("\n");
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
