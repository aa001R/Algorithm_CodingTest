import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayList<Integer> stack = new ArrayList<Integer>();
        stack.add(arr[0]);
        for (int num : arr) {
            if (stack.get(stack.size() - 1) != num) {
                stack.add(num);
            }
        }
        return stack.stream().mapToInt(Integer::intValue).toArray();
    }
}