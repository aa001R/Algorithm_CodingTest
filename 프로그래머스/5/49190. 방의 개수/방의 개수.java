import java.util.*;

class Solution {
    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) return false;
            Point p = (Point) o;
            return this.x == p.x && this.y == p.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Edge {
        Point from, to;
        Edge(Point from, Point to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Edge)) return false;
            Edge e = (Edge) o;
            // 양방향이므로 순서 상관없이 동일해야 함
            return (from.equals(e.from) && to.equals(e.to)) || (from.equals(e.to) && to.equals(e.from));
        }

        @Override
        public int hashCode() {
            return from.hashCode() + to.hashCode(); // 순서 무시
        }
    }

    public int solution(int[] arrows) {
        int[][] dir = {
            {0, 1}, {1, 1}, {1, 0}, {1, -1},
            {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}
        };
        
        Set<Point> nodes = new HashSet<>();
        Set<Edge> edges = new HashSet<>();
        int roomCount = 0;

        Point cur = new Point(0, 0);
        nodes.add(cur);

        for (int arrow : arrows) {
            for (int i = 0; i < 2; i++) { // 방향을 2번 이동 (mid-point 처리용)
                int nx = cur.x + dir[arrow][0];
                int ny = cur.y + dir[arrow][1];
                Point next = new Point(nx, ny);

                Edge e = new Edge(cur, next);
                if (nodes.contains(next) && !edges.contains(e)) {
                    roomCount++;
                }

                nodes.add(next);
                edges.add(e);
                cur = next;
            }
        }

        return roomCount;
    }
}
