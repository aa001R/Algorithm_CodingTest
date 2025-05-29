import java.util.*;

class Solution {
    static int N;
    static final int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
    public int solution(int[][] game_board, int[][] table) {
        N = game_board.length;
        // 1. 빈칸 추출
        List<List<int[]>> emptySpace = extractShapes(game_board, 0);
        // 2. 조각 추출
        List<List<int[]>> puzzlePieces = extractShapes(table, 1);
        
        // 3. 빈칸마다 조각 맞추기
        // 사용한 퍼즐 표시
        boolean [] used = new boolean[puzzlePieces.size()];
        int answer = 0;

        for (List<int []> space : emptySpace) {
            for (int i = 0; i < puzzlePieces.size(); i++) {
                if (used[i]) continue; // 사용한 퍼즐
                
                List<int[]> piece = puzzlePieces.get(i);
                boolean matched = false;
                // 회전
                for (int r = 0; r < 4; r++) {
                    // 빈칸 맞는지 확인
                    if (isSameShape(space, normalize(piece))){
                        answer += space.size();
                        used[i] = true;
                        matched = true;
                        break;
                    }
                    // 90도 회전
                    piece = rotate(piece);
                }

                if (matched) break; // 일치했으면 다른 빈칸 점검
            }
        }
        
        return answer;
    }
    
    // 전체 모양 추출
    private List<List<int[]>> extractShapes(int[][] board, int target) {
        boolean [][] visited = new boolean[N][N];
        List<List<int[]>> shapes = new ArrayList<>();
               
        for (int r = 0; r < N; r++) {
            for (int c=  0; c < N; c++){
                if(!visited[r][c] && board[r][c] == target){
                    shapes.add(normalize(bfs(r, c , board, visited, target)));
                }
            }
        }
        
        return shapes;
    }
    
    // 각 도형 탐색 BFS (각 도형별 좌표값)
    private List<int[]> bfs(int r, int c, int[][] board, boolean[][] visited, int target) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        List<int[]> shape = new ArrayList<>();

        visited[r][c] = true;
        queue.offer(new int[]{r, c});
        shape.add(new int[]{r, c});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : directions) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (isOut(nr, nc) || visited[nr][nc] || board[nr][nc] != target) continue;
                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
                shape.add(new int[]{nr, nc});
            }
        }
        return shape;
    }
    
    // 배열 내부 좌표 여부 확인
    private boolean isOut(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
    
    // 좌상단 기준 정규화
    private List<int[]> normalize(List<int[]> shape) {
        int minR = Integer.MAX_VALUE, minC = Integer.MAX_VALUE;
        // 배열 그림 상 좌상단 좌표 찾기
        for (int [] p : shape) {
            minR = Math.min(p[0], minR);
            minC = Math.min(p[1], minC);
        }
        
        List<int[]> normalized = new ArrayList<>();
        // (2, 0),(2, 1),(3, 0) -> (0, 0),(0, 1),(1, 0) 로 정규화
        for (int[] p : shape) {
            normalized.add(new int[]{p[0] - minR, p[1] - minC});
        }
        normalized.sort(Comparator.<int[]>comparingInt(a -> a[0])
                                  .thenComparingInt(a -> a[1]));
        return normalized;
    }
    
    // 회전 (90도)
    private List<int[]> rotate(List<int[]> shape) {
        int maxR = 0;
        // 회전 시 음수 좌표를 피하고자 배열 그림 상 최하단 탐색
        for (int[] p : shape) {
            maxR = Math.max(maxR, p[0]);
        }

        List<int[]> rotated = new ArrayList<>();
        for (int[] p : shape) {
            int r = p[0], c = p[1];
            // 시계 방향 90도 회전: (x, y) -> (y, -x + maxR) 
            // maxR를 통해 음수 좌표 양수로 보정
            rotated.add(new int[]{c, maxR - r});
        }
        return rotated;
    }

    
    // 도형 좌표 비교 
    boolean isSameShape(List<int[]> a, List<int[]> b) {
        if (a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0] || a.get(i)[1] != b.get(i)[1]) {
                return false;
            }
        }
        return true;
}

}