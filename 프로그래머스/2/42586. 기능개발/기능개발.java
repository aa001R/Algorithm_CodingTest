import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int progressCnt = progresses.length;
        int [] finishDays = new int[progressCnt];
        for (int i = 0; i < progressCnt; i++) {
            finishDays[i] = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
        }
        // 7 3 9 - 2 1
        // 5 10 1 1 20 1 - 1 3 2
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(1);
        int idx = 0;
        int pre = finishDays[0];
        for (int i = 1; i < progressCnt; i++) {
            if (finishDays[i] <= pre) {
                answer.set(idx, answer.get(idx)+1);
            } else {
                answer.add(1);
                ++idx;
                pre = finishDays[i];
            }
        }
        return answer.stream().mapToInt(i->i).toArray();
    }
}