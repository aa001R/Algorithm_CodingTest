import java.util.*;

class Solution {
    /**
     * 슬라이딩 윈도우(투 포인터)로 모든 종류 포함하는 최소 구간을 찾음.
     */
    public int[] solution(String[] gems) {
        int n = gems.length;
        // 서로 다른 보석 종류 수
        int required = new HashSet<>(Arrays.asList(gems)).size();
        // 현재 윈도우 안의 보석별 카운트
        Map<String, Integer> cnt = new HashMap<>();
        
        int bestLen = 100_000; // 최단 길이
        int bestL = 0, bestR = 0;        // 최단 구간 (0-based)
        int l = 0; // 왼쪽 포인터
        for (int r = 0; r < n; r++) {
            cnt.put(gems[r], cnt.getOrDefault(gems[r], 0) + 1);
            // 현재 윈도우가 모든 종류를 포함한다면 왼쪽을 가능한 만큼 당겨서 최소화 시도
            while (cnt.size() == required && l <= r) {
                int curLen = r - l + 1;
                // 길이가 더 짧거나 (같은 길이일 때 시작 인덱스가 더 작으면) 갱신
                if (curLen < bestLen || (curLen == bestLen && l < bestL)) {
                    bestLen = curLen;
                    bestL = l;
                    bestR = r;
                }
                // 왼쪽 요소 하나 제거(수축)
                String leftGem = gems[l];
                int c = cnt.get(leftGem);
                if (c == 1) cnt.remove(leftGem);
                else cnt.put(leftGem, c - 1);
                l++; // 왼쪽 포인터 이동
            }
        }
        // 결과는 1-based index 반환
        return new int[]{bestL + 1, bestR + 1};
    }
}
