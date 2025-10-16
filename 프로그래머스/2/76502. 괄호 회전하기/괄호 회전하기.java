import java.util.*;

class Solution {
    public int solution(String s) {
        int n = s.length();
        String doubled = s + s;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (isValid(doubled.substring(i, i + n))) count++;
        }
        return count;
    }

    private boolean isValid(String str) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char open = stack.pop();
                if (!matches(open, c)) return false;
            }
        }
        return stack.isEmpty();
    }

    private boolean matches(char open, char close) {
        return (open == '(' && close == ')')
            || (open == '[' && close == ']')
            || (open == '{' && close == '}');
    }
}
