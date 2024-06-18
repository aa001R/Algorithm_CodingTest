import java.io.*;
import java.util.*;

public class Main {
	final static long mod = 1_000_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long [][] dp = new long[n+1][10];
		for(int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}
		for(int digit = 2; digit <= n; digit++) {
			for(int num = 0; num <= 9; num++) {
				if(num == 0) {
					dp[digit][num] = dp[digit-1][1] % mod;
				}else if(num == 9) {
					dp[digit][num] = dp[digit-1][8] % mod;
				}else {
					dp[digit][num] = (dp[digit-1][num-1] + dp[digit-1][num+1] )% mod;
				}
			}
		}
		long result = 0;
		for(int i = 0; i <= 9; i++) {
			result += dp[n][i];
		}
		System.out.println(result%mod);
	}
}