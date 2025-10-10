class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for (int i = 0; i < n; i++){
            // 두 지도 행 합치기
            int merged = arr1[i] | arr2[i];
            // 0, 1 검사 및 # 변환
            StringBuilder sb = new StringBuilder();
            for (int j = n - 1; j >= 0; j--) {
                if ((merged & (1 << j)) == 0) sb.append(" ");
                else sb.append("#");
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
}