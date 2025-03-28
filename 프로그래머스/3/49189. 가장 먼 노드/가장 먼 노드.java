import java.util.*;

class Solution {
    
    class Node {
        int no;
        Node next;        
        Node (int no, Node next) {
            this.no = no;
            this.next = next;
        }
    }
    
    public int solution(int n, int[][] edge) {
        Node [] graph = new Node [n+1];
        for (int i = 0; i < edge.length; i++) {
            int from = edge[i][0];
            int to = edge[i][1];
            graph[from] = new Node(to, graph[from]);
            graph[to] = new Node(from, graph[to]);
        }
        
        // bfs
        ArrayDeque<Integer> que = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        que.offer(1);
        visited[1] = true;
        int farNode = 1;
        while(!que.isEmpty()) {
            farNode = que.size();
            for (int size = que.size(); size > 0; size--) {
                int cur = que.poll();
                for (Node temp = graph[cur]; temp != null; temp = temp.next) {
                    if (visited[temp.no]) continue;
                    visited[temp.no] = true;
                    que.offer(temp.no);
                }
                
            }
        }
        return farNode;
    }
}