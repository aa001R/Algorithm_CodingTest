class Solution {
    int[][] memo;
    int[][] triangle;

    public int solution(int[][] triangle) {
        int h = triangle.length;
        this.triangle = triangle;
        this.memo = new int[h][h];

        return dfs(0, 0); // 꼭대기부터 시작
    }

    private int dfs(int i, int j) {
        // 마지막 줄이면 자기 자신 반환
        if (i == triangle.length - 1) return triangle[i][j];

        // 이미 계산한 값이면 바로 반환
        if (memo[i][j] != 0) return memo[i][j];

        // 아래 두 경로 중 큰 값 선택
        int left = dfs(i + 1, j);
        int right = dfs(i + 1, j + 1);
        memo[i][j] = triangle[i][j] + Math.max(left, right);

        return memo[i][j];
    }
}
