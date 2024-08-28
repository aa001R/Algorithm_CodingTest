import java.io.*;

public class Main {
    static int MOD = 100000;
    static int NORTH = 0, EAST = 1;
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int w = read();
        int h = read();
        //[(방향) NORTH 0, EAST 1][w][h]
        int[][][] dp = new int[2][w + 1][h + 1]; 
        dp[0][1][1] = dp[1][1][1] = 1;
        for (int x = 1; x <= w; x++) {
            for (int y = 1; y <= h; y++) {
            	dp[NORTH][x][y] += dp[NORTH][x][y-1] + dp[EAST][x-1][y-1] % MOD;
                dp[EAST][x][y] += dp[EAST][x-1][y] + dp[NORTH][x-1][y-1] % MOD;
            }
        }
        int result = (dp[NORTH][w][h] + dp[EAST][w][h]) % MOD; 
        bw.append(Integer.toString(result));
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