class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left + 1)];
        for(long k = left; k <= right; k++){
            int row = (int)(k / n);
            int col = (int)(k % n);
            answer[(int)(k - left)] = Math.max(row, col) + 1;
        }
        return answer;
    }
}