import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int H = read(), W = read();
		int [][] cloud = new int[H][W];
		for (int i = 0; i < H; i++) {
			String row = br.readLine();
			int preCloud = -1;
			for (int j = 0; j < W; j++) {
				if (row.charAt(j) == 'c') {
					preCloud = j;
					cloud[i][j] = 0;
				} else if (preCloud == -1) {
					cloud[i][j] = preCloud;
				} else {
					cloud[i][j] = j - preCloud;
				}
				bw.append(Integer.toString(cloud[i][j])).append(" ");
			}
			bw.append("\n");
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
