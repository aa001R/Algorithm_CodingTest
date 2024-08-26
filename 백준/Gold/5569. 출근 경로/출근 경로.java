import java.io.*;

public class Main {
    static int mod = 100000;
    static int NORTH = 0, EAST = 1;
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int w = read();
        int h = read();
        //[h][w][방향, 0은 북 1은 동],[방향 변경 여부, 0은 꺾지 않은경우 1은 꺾은 경우]
        int[][][][] dp = new int[w + 1][h + 1][2][2];
        for(int i = 1; i <= w; i++) {
            dp[i][1][0][0] = 1;
        } 
        for(int i = 1; i <= h; i++) {
            dp[1][i][1][0] = 1;
        }
 
        for(int i = 2; i <= w; i++) {
            for(int j = 2; j <= h; j++) {
                dp[i][j][1][0] = (dp[i][j - 1][1][1] + dp[i][j - 1][1][0]) % mod;
                dp[i][j][1][1] = dp[i][j - 1][0][0] % mod;
                dp[i][j][0][0] = (dp[i - 1][j][0][0] + dp[i - 1][j][0][1]) % mod;
                dp[i][j][0][1] = dp[i - 1][j][1][0];
            }
        }
        int result = (dp[w][h][0][0] + dp[w][h][0][1] + dp[w][h][1][0] + dp[w][h][1][1]) % mod; 
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