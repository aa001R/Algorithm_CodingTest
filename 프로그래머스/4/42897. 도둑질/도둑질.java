class Solution {
    public int solution(int[] money) {
        if (money.length == 3)
            return Math.max(money[0], Math.max(money[1], money[2]));
        return Math.max(robHouse(0, money.length - 2, money), robHouse(1, money.length - 1, money));
    }
    
    // 일직선 배치에서의 최대 금액을 구하는 함수
    private int robHouse(int start, int end, int [] money){
        int [] dp = new int[money.length];
        dp[start] = money[start];
        dp[start + 1] = Math.max(money[start], money[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
        }
        return dp[end];
    }
}