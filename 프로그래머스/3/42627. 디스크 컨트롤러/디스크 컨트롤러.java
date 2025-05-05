import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;

        // 작업이 요청되는 시점 기준으로 오름차순 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        // 작업의 소요시간 기준으로 오름차순 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        int now = 0; // 현재 시간
        int jobIndex = 0; // 작업 배열 인덱스
        int completed = 0; // 처리 완료된 작업 개수

		// 모든 작업을 처리했다면 종료
        while (completed < jobs.length) {
        
            // 현재 시간까지 들어온 작업(이전 작업 처리 중 요청된 작업)을 모두 우선순위 큐에 추가
            while (jobIndex < jobs.length && jobs[jobIndex][0] <= now) {
                pq.add(jobs[jobIndex++]);
            }

            if(!pq.isEmpty()) { // 이전 작업 처리 중 요청된 작업이 있는 경우
                int[] job = pq.poll();
                now += job[1]; // 작업 수행 후 시간 증가
                answer += now - job[0]; // 요청 시점부터 종료까지 걸린 시간 누적
                completed++; // 처리 완료된 작업 개수 1 증가
            } else { // 이전 작업 처리 중 요청된 작업이 없는 경우
                // 처리할 작업이 없다면 다음 작업 요청 시간으로 점프
                now = jobs[jobIndex][0];
            }
        }

        return answer / jobs.length;
    }
}