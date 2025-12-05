import java.util.HashMap;

class Solution {
    /**
     * 모든 로봇의 이동을 시뮬레이션하여 같은 좌표에 2대 이상 모이는(위험) 상황이 발생한
     * 총 횟수를 반환한다.
     */
    public int solution(int[][] points, int[][] routes) {
        int answer = 0; // 위험 상황 카운터

        int x = routes.length;      // 로봇 수
        int m = routes[0].length;   // 각 로봇 경로 길이

        int[] curR = new int[x];    // 현재 r 좌표
        int[] curC = new int[x];    // 현재 c 좌표
        int[] targetIdx = new int[x]; // 다음 목표 인덱스 (route 내 인덱스)
        boolean[] finished = new boolean[x]; // 더 이상 센터에 없는지 여부
        boolean[] willFinish = new boolean[x]; // 이번 이동으로 마지막 포인트에 도달해 다음 카운트 후 떠날 예정인지

        // 초기화: 각 로봇의 시작 좌표 설정, 다음 목표는 routes[i][1]
        for (int i = 0; i < x; i++) {
            int start = routes[i][0] - 1;      // routes는 1-based 포인트 번호
            curR[i] = points[start][0];        // 시작 r
            curC[i] = points[start][1];        // 시작 c
            targetIdx[i] = 1;                  // 두 번째 포인트부터 이동
        }

        // 시뮬레이션: 모든 로봇이 떠날 때까지 1초씩 진행
        while (true) {
            // 현재 좌표별 로봇 수 집계
            HashMap<Integer, Integer> map = new HashMap<>();
            int activeCount = 0;
            for (int i = 0; i < x; i++) {
                if (!finished[i]) {
                    activeCount++;
                    int key = curR[i] * 1000 + curC[i]; // 좌표 인코딩 (r,c ≤ 100 이므로 안전)
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
            }

            // 활성 로봇이 없으면 종료
            if (activeCount == 0) break;

            // 같은 좌표에 2대 이상인 좌표마다 1번씩 증가
            for (int cnt : map.values()) if (cnt >= 2) answer++;

            // 이전 시간에 '마지막 포인트'에 도달했던 로봇들을 이제 제거
            for (int i = 0; i < x; i++) {
                if (willFinish[i]) {
                    finished[i] = true;
                    willFinish[i] = false;
                }
            }

            // 다음 초로 이동: 각 로봇을 r 우선 규칙에 따라 한 칸 이동
            for (int i = 0; i < x; i++) {
                if (finished[i]) continue;
                if (targetIdx[i] >= m) continue; // 목표가 더 없으면 이동 없음

                int tgt = routes[i][targetIdx[i]] - 1;
                int tr = points[tgt][0];
                int tc = points[tgt][1];

                // r 먼저 이동
                if (curR[i] != tr) {
                    if (curR[i] < tr) curR[i]++;
                    else curR[i]--;
                } else if (curC[i] != tc) {
                    // r 같으면 c 이동
                    if (curC[i] < tc) curC[i]++;
                    else curC[i]--;
                }

                // 목적지 도달 확인 및 인덱스 증가; 마지막 도착이면 willFinish 플래그 설정
                if (curR[i] == tr && curC[i] == tc) {
                    if (targetIdx[i]++ == m - 1) willFinish[i] = true;
                }
            }
        }

        return answer;
    }
}
