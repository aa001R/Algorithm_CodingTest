import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int n = elements.length;
        int[] extended = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            extended[i] = elements[i % n];
        }
        // 누적합
        int[] prefix = new int[2 * n + 1];
        for (int i = 0; i < 2 * n; i++) {
            prefix[i + 1] = prefix[i] + extended[i];
        }

        // 길이 i인 부분합
        for (int len = 1; len <= n; len++) {
            for (int start = 0; start < n; start++) {
                set.add(prefix[start + len] - prefix[start]);
            }
        }
        return set.size();
    }
}