import java.util.*;

class Solution {
    public int solution(int[] arrows) {
        // 8방향
        int[][] dir = {
            {0, 1}, {1, 1}, {1, 0}, {1, -1},
            {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}
        };

        Set<String> nodes = new HashSet<>(); // 방문한 점
        Set<String> edges = new HashSet<>(); // 방문한 선분
        int roomCount = 0;

        int x = 0, y = 0;
        nodes.add(posStr(x, y));

        for (int arrow : arrows) {
            for (int i = 0; i < 2; i++) { // 교차 방지: 방향마다 2번 이동
                int nx = x + dir[arrow][0];
                int ny = y + dir[arrow][1];

                String from = posStr(x, y);
                String to = posStr(nx, ny);
                String edge1 = from + ":" + to;
                String edge2 = to + ":" + from; // 양방향 처리

                if (nodes.contains(to) && !edges.contains(edge1) && !edges.contains(edge2)) {
                    roomCount++;
                }

                nodes.add(to);
                edges.add(edge1);
                edges.add(edge2);

                x = nx;
                y = ny;
            }
        }

        return roomCount;
    }

    private String posStr(int x, int y) {
        return x + "," + y;
    }
}
