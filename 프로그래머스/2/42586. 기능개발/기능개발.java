import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();
        
        // Step 1: 각 작업의 완료일 계산
        for (int i = 0; i < progresses.length; i++) {
            int remain = 100 - progresses[i];
            int days = (remain + speeds[i] - 1) / speeds[i]; // 올림 처리
            queue.offer(days);
        }

        List<Integer> result = new ArrayList<>();
        
        // Step 2: 배포 시뮬레이션
        while (!queue.isEmpty()) {
            int current = queue.poll();
            int count = 1;
            
            // 뒤에 있는 작업들이 앞 작업과 같이 배포될 수 있는지 확인
            while (!queue.isEmpty() && queue.peek() <= current) {
                queue.poll();
                count++;
            }
            result.add(count);
        }

        // 결과 변환
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
