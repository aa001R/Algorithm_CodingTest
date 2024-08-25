import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = read();
		int m = read();
		int [][] dp = new int[31][11];
		// nHm-n = m-1Cn-1 = m-2Cn-2 + m-2Cn-1 구하면 됨 
		// m-1을 기준으로 하기에 0부터 시작
		for(int i = 0; i < m; i++){
			dp[i][0] = 1;
		}
		for(int i = 1; i < m; i++){
			for(int j = 1; j < n; j++){
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
			}
		}
		bw.append(Integer.toString(dp[m-1][n-1]));
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