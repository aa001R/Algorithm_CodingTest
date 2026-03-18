import java.io.*;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int [][] delta = {{-2, 0},{2, 0},{-1, 1},{1, 1},{-1, -1},{1, -1}};
		int [][][] dp = new int[15][57][29];
		dp[0][28][14] = 1;
		for (int cnt = 1; cnt <= 14; cnt++){
			for (int x = 0; x < 57; x++){
				for (int y = 0; y < 29; y++){
					if (dp[cnt - 1][x][y] == 0) continue;
					for (int [] d : delta){
						int nx = x+d[0], ny = y+d[1];
						if (isOut(nx, ny)) continue;
						dp[cnt][nx][ny] += dp[cnt - 1][x][y];
					}
				}
			}
		}
		int T = read();
		for (int t = 0; t < T; t++){
			int n = read();
			bw.append(Integer.toString(dp[n][28][14]));
			bw.newLine();
		}
		bw.flush();
	}

	public static boolean isOut(int x, int y){
		return x < 0 || x >= 57 || y < 0 || y >= 29;
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if(isNegative) n = System.in.read() & 15;
		while((cur = System.in.read()) > 32) n = (n << 3) + (n << 1) + (cur & 15);
		return isNegative? -n : n;
	}
}
