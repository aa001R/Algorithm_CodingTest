import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;
        long sum1 = 0, sum2 = 0;
        // 큐 합 계산
        for (int num : queue1) sum1 += num;
        for (int num : queue2) sum2 += num;
        long total = sum1 + sum2;

        // 총합이 홀수면 불가능
        if (total % 2 != 0) return -1;
        long target = total / 2;
        
        // 두 큐 연결 배열
        long[] arr = new long[2 * n];
        for (int i = 0; i < n; i++) {
            arr[i] = queue1[i];
            arr[n+i] = queue2[i];
        }
        
        int maxMove = 2 * n * 2; // 최대 이동 횟수
        int start = 0, end = n;   // 초기 구간: queue1 전체
        long curSum = sum1;
        int answer = 0;
        while (start < 2 * n && end < 2 * n) {
            if (curSum == target) {
                return answer;
            } else if (curSum < target) {
                curSum += arr[end];
                end++;
            } else {
                curSum -= arr[start];
                start++;
            }
            answer++;
            if (answer > maxMove) return -1; // 불가능한 경우
        }
        return -1;
    }
}
