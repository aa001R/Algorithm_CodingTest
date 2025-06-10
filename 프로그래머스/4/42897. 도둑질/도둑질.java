class Solution {
    public int solution(int[] money) {
        int n = money.length;
        if (n == 3) {
            return Math.max(money[0], Math.max(money[1], money[2]));
        }
        
        // Case 1: 첫 집 고려, 마지막 집 제외 (0 ~ n-2)
        // Case 2: 첫 집 제외, 마지막 집 고려 (1 ~ n-1)  
        return Math.max(robHouse(money, 0, n-2), robHouse(money, 1, n-1));
    }
    
    // 일직선 배치에서의 최대 금액을 구하는 함수
    private int robHouse(int[] money, int start, int end){
        int [] dp = new int[money.length];
        dp[start] = money[start];
        dp[start + 1] = Math.max(money[start], money[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
        }
        return dp[end];
    }
}