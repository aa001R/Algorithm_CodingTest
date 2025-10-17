class Solution {
    public int solution(int n) {
        if (n % 2 != 0) return 0;
        long [] dp = new long[n+1];
        int MOD = 1_000_000_007;
        dp[0] = 1L;
        dp[2] = 3L;
        for(int i = 4; i <= n; i+=2) {
			dp[i] = dp[i-2] * dp[2] % MOD;
			for(int j = i-4; j >= 0; j-=2) {
				dp[i] = (dp[i] + dp[j] * 2 % MOD) % MOD; 
			}
		}
        return (int) dp[n];
    }
}