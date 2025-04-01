import java.util.*;

public class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new int[]{i, priorities[i]});
            pq.offer(priorities[i]);
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[1] == pq.peek()) {
                answer++;
                pq.poll();
                if (current[0] == location) break;
            } else {
                queue.offer(current);
            }
        }

        return answer;
    }
}
