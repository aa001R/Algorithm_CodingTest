import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = read();
		int W = read();
		int [][] dp = new int[T+1][W+1];
		for(int t = 1; t <= T; t++) {
			int plumTree = read();
			if(plumTree == 1) dp[t][0] = dp[t-1][0] + 1;
			else dp[t][0] = dp[t-1][0];
			for(int w = 1; w <= W; w++) {
				if(w%2+1 == plumTree) dp[t][w] = Math.max(dp[t-1][w-1], dp[t-1][w]) + 1; 
				else dp[t][w] = Math.max(dp[t-1][w-1], dp[t-1][w]);
			}
		}
		int max = dp[T][0];
		for(int w = 1; w <= W; w++) {
			max = Math.max(max, dp[T][w]);
		}
		bw.append(Integer.toString(max));
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