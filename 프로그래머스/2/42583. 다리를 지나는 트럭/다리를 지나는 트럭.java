import java.util.*;
class Solution {
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();
        int time = 0;
        int totalWeight = 0;
        int i = 0;

        // 초기 다리 상태: 0으로 채움
        for (int j = 0; j < bridge_length; j++) {
            bridge.offer(0);
        }

        while (!bridge.isEmpty()) {
            time++; // 1초 경과
            totalWeight -= bridge.poll(); // 한 칸 앞으로 이동하며 맨 앞 트럭 제거

            if (i < truck_weights.length) {
                if (totalWeight + truck_weights[i] <= weight) {
                    bridge.offer(truck_weights[i]);
                    totalWeight += truck_weights[i];
                    i++; // 다음 트럭으로 이동
                } else {
                    bridge.offer(0); // 트럭을 못 올리는 경우 빈 공간 (0) 추가
                }
            }
        }

        return time;
    }
}