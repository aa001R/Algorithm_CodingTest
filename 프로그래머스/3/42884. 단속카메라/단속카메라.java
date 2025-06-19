import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        // 진출 지점 기준으로 정렬
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));

        int camera = Integer.MIN_VALUE; // 가장 최근 설치한 카메라 위치
        int count = 0;
        
        for (int[] route : routes) {
            int entry = route[0];
            int exit = route[1];

            // 현재 차량이 기존 카메라에 안 걸리면 새 카메라 설치
            if (camera < entry) {
                camera = exit;  // 이 차량의 진출 지점에 카메라 설치
                count++;
            }
        }

        return count;
    }
}
