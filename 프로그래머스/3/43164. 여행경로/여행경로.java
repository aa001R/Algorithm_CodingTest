import java.util.*;

class Solution {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    LinkedList<String> path = new LinkedList<>();

    public String[] solution(String[][] tickets) {
        for (String[] ticket : tickets) {
            graph.computeIfAbsent(ticket[0], k -> new PriorityQueue<>())
                 .add(ticket[1]);
        }

        dfs("ICN");

        return path.toArray(new String[0]);
    }

    void dfs(String airport) {
        PriorityQueue<String> arrivals = graph.get(airport);

        while (arrivals != null && !arrivals.isEmpty()) {
            dfs(arrivals.poll()); // 사전순 우선 방문
        }

        path.addFirst(airport); // 역순으로 추가
    }
}
