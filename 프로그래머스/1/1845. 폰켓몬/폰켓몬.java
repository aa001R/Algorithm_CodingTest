import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] nums) {
        HashSet<Integer> set = new HashSet<>(); 
        for (int num : nums) {
            set.add(num);
        }
        return Math.min(set.size(), nums.length / 2);
    }
}