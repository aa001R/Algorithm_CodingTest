import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int left = 1;
        int right = distance;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int prev = 0; // 이전 바위(또는 시작점)
            int removed = 0;

            for (int rock : rocks) {
                if (rock - prev < mid) {
                    removed++; // 거리 짧으면 제거
                } else {
                    prev = rock; // 거리 충분하면 통과
                }
            }

            // 마지막 거리 (도착지점까지)
            if (distance - prev < mid) removed++;

            if (removed <= n) {
                // 거리 만족 → 더 늘려보기
                answer = mid;
                left = mid + 1;
            } else {
                // 거리 안됨 → 줄여야 함
                right = mid - 1;
            }
        }

        return answer;
    }
}
