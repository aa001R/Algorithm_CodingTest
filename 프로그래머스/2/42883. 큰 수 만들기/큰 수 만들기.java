import java.util.*;
class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        ArrayDeque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < number.length(); i++) {
            while( !deque.isEmpty() && deque.peek() < number.charAt(i) && k > 0) {
                    deque.pop();
                    k--;
            }
            deque.push(number.charAt(i));
        }
        while (deque.size() - k > 0) {
            answer.append(deque.pollLast());
        }
        return answer.toString();
    }
}