import java.util.*;
/*
n개 퍼즐
난이도 소요시간
숙련도 -> 틀리는 횟수 다름
diff <= level
- cur 만큼 시간 사용 해결
diff > level
- diff - level 번 틀리고, 틀릴 때마다 * ( cur 만큼 시간 소요 + pre 시간 만큼 이전 퍼즐 해결)
- 이후 cur 만큼 시간 사용 해결
퍼즐을 모두 해결하기 위한 최소 숙련도 구하기
*/
class Solution {
    // time[i] = i 순서의 cur 시간
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1, right = 0;
        for(int diff : diffs){
            right = Math.max(right, diff+1);
        }
        while(left < right){
            int mid = (left + right) / 2;
            if(canSolved(mid, diffs, times, limit)) right = mid;
            else left = mid + 1;
        }
        return left;
    }
    private boolean canSolved(int level, int[] diffs, int[] times, long limit){
        long totalT = 0;
        int pre = 0;
        for(int i = 0; i < diffs.length; i++){
            if(totalT > limit) return false;
            if(diffs[i] > level){
                totalT += (pre + times[i]) * (diffs[i] - level);
            }
            totalT += times[i];
            pre = times[i];
        }
        return totalT <= limit;
    }
}