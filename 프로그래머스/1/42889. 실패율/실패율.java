import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] stuck = new int[N + 2];
        for (int s : stages) stuck[s]++;

        int total = stages.length;
        double[] failRate = new double[N + 1];  // 1-based index
        for (int i = 1; i <= N; i++) {
            if (total == 0) failRate[i] = 0;
            else failRate[i] = (double) stuck[i] / total;
            total -= stuck[i];
        }

        Integer[] stageNums = new Integer[N];
        for (int i = 0; i < N; i++) stageNums[i] = i + 1;

        // 실패율 기준 내림차순, 동일하면 스테이지 번호 오름차순
        Arrays.sort(stageNums, (a, b) -> {
            if (failRate[b] == failRate[a]) return a - b;
            return Double.compare(failRate[b], failRate[a]);
        });

        return Arrays.stream(stageNums).mapToInt(Integer::intValue).toArray();
    }
}
