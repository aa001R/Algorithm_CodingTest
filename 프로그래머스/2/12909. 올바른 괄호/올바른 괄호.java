import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if ('(' == c) {
                stack.push(c);
            }
            else {
                if (stack.isEmpty() || '(' != stack.pop()) {
                    answer = false;
                    break;
                }
            }
        }
        
        if(!stack.isEmpty()){
            answer = false;
        }

        return answer;
    }
}