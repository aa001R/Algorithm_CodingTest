import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int xs = read(), ys = read();
		int xe = read(), ye = read(), dx = read(), dy = read();
		double t = Math.max(0, (dx*(xs-xe) + dy*(ys-ye)) / (double)(dx*dx + dy*dy));
		int x = (int)Math.round(xe + dx*t);
		int y = (int)Math.round(ye + dy*t);
		bw.append(Integer.toString(x)).append(" ").append(Integer.toString(y));
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
