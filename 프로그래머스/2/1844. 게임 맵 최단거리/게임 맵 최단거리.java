import java.util.*;

class Solution {
    static int n, m;

    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        int[][] gCost = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        for (int[] row : gCost) Arrays.fill(row, Integer.MAX_VALUE);
        gCost[0][0] = 1;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 1, h(0, 0)));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int r = cur.r, c = cur.c;

            if (visited[r][c]) continue;
            visited[r][c] = true;

            if (r == n-1 && c == m-1) return cur.g;

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (maps[nr][nc] == 0 || visited[nr][nc]) continue;

                int newG = cur.g + 1;
                if (newG < gCost[nr][nc]) {
                    gCost[nr][nc] = newG;
                    pq.offer(new Node(nr, nc, newG, h(nr, nc)));
                }
            }
        }

        return -1;
    }

    // 맨해튼 거리
    private int h(int r, int c) {
        return Math.abs(n - 1 - r) + Math.abs(m - 1 - c);
    }

    class Node implements Comparable<Node> {
        int r, c;
        int g; // 시작점부터 현재까지의 거리
        int f; // g + h

        Node(int r, int c, int g, int h) {
            this.r = r;
            this.c = c;
            this.g = g;
            this.f = g + h;
        }

        @Override
        public int compareTo(Node o) {
            return this.f - o.f;
        }
    }
}
