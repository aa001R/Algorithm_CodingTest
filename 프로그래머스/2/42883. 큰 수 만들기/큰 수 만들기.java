import java.util.*;
class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < number.length(); i++) {
            while( !stack.isEmpty() && stack.peek() < number.charAt(i) && k > 0) {
                    stack.pop();
                    k--;
            }
            stack.push(number.charAt(i));
        }
        while( !stack.isEmpty() && k > 0) {
            stack.pop();
            k--;
        }
        while( !stack.isEmpty()) {
            answer.append(stack.pop());
        }
        return answer.reverse().toString();
    }
}