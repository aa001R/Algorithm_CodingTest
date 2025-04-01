import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // 가격이 떨어지는 시점 발견
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int prev = stack.pop();
                answer[prev] = i - prev;
            }
            stack.push(i);
        }

        // 끝까지 가격이 떨어지지 않은 경우 처리
        while (!stack.isEmpty()) {
            int prev = stack.pop();
            answer[prev] = n - 1 - prev;
        }

        return answer;
    }
}