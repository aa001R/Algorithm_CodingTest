import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        // 그래프 초기화 (사전순 유지를 위해 PriorityQueue 사용)
        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for (String[] ticket : tickets) {
            graph.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
        }

        ArrayDeque<String> stack = new ArrayDeque<>();
        LinkedList<String> route = new LinkedList<>();

        stack.push("ICN");

        while (!stack.isEmpty()) {
            String curr = stack.peek();
            PriorityQueue<String> arrivals = graph.get(curr);

            // 더 이상 갈 곳이 없다면 경로에 추가 (역순)
            if (arrivals == null || arrivals.isEmpty()) {
                route.addFirst(curr);
                stack.pop();
            } else {
                stack.push(arrivals.poll()); // 사전순 도착지 먼저 탐색
            }
        }

        return route.toArray(new String[0]);
    }
}
