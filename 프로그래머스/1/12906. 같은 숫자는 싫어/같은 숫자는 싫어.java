import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayList<Integer> stack = new ArrayList<Integer>();
        int preNum = 10;
        for (int num : arr) {
            if (preNum != num) {
                stack.add(num);
            }
            preNum = num;
        }
        int [] answer = new int [stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            answer[i] = stack.get(i);
        }
        return answer;
    }
}