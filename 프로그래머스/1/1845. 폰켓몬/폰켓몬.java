import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int ponkemonCnt = nums.length;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < ponkemonCnt; i++){
            set.add(nums[i]);
        }
        if (ponkemonCnt / 2 <= set.size()) {
            return ponkemonCnt / 2;
        } else {
            return set.size();
        }
    }
}