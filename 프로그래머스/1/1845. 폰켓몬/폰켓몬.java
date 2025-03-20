import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] nums) {
        return Arrays.stream(nums) 
                .boxed()
                .collect(Collectors.collectingAndThen(Collectors.toSet(),
                        ponkemons -> Integer.min(ponkemons.size(), nums.length / 2)));

    }
}