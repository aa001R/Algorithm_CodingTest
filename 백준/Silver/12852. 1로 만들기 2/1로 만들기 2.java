import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = read();
		int [][] dp = new int[2][n+1];
		for(int i = 1; i <= n; i++) {
			dp[1][i] = i;
			if(i > 1) {
				dp[0][i] = 1 + dp[0][i-1];
				dp[1][i] = i - 1;
			}
			if(i % 2 == 0 && (1 + dp[0][i/2]) < dp[0][i]) {
				dp[0][i] = 1 + dp[0][i/2];
				dp[1][i] = i / 2;
			}
			if(i % 3 == 0 && (1 + dp[0][i/3]) < dp[0][i]){
				dp[0][i] = 1 + dp[0][i/3];
				dp[1][i] = i / 3;
			}
		}
		bw.append(Integer.toString(dp[0][n])).append("\n");
		bw.append(Integer.toString(n)).append(" ");
		for(int i = n; i > 1; i = dp[1][i]) {
			bw.append(Integer.toString(dp[1][i])).append(" ");
		}
        bw.flush();
    }

	private static int read() throws Exception {
		int n = 0;
		int cur;
		boolean isNumber = false;
		while(true) {
			cur = System.in.read();
			if(cur <= 32) {
				if(isNumber) return n;
			}else {
				isNumber = true;
				n = (n<<3)+(n<<1)+(cur&15);
			}
		}
	}
}