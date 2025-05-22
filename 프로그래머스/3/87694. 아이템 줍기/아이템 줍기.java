import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int size = 102;
        int [][] board = new int[size][size];
        int [][] delta = {{1, 0},{-1, 0},{0, 1},{0, -1}};
        // 1. 모든 좌표 2배
        for (int [] rect : rectangle) {
            int x1 = rect[0] * 2, y1 = rect[1] * 2;
            int x2 = rect[2] * 2, y2 = rect[3] * 2;
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++){
                    board[x][y] = 1;                    
                }
            }
        }
        
        // 2. 직사각형 내부를 -1로 덮기
        for (int [] rect : rectangle) {
            int x1 = rect[0] * 2, y1 = rect[1] * 2;
            int x2 = rect[2] * 2, y2 = rect[3] * 2;
            for (int x = x1 + 1; x < x2; x++) {
                for (int y = y1 + 1; y < y2; y++){
                    board[x][y] = -1;                    
                }
            }
        }
        
        // 3. 아이템 탐색
        ArrayDeque<int []> que = new ArrayDeque<>();
        que.offer(new int[] {characterX*2, characterY*2, 0});
        board[characterX*2][characterY*2] = -2; // 지나간 자리 표시
        
        while(!que.isEmpty()){
            int[] cur = que.poll();
            int x = cur[0], y = cur[1], dist = cur[2];

            if (x == itemX * 2 && y == itemY * 2) return dist / 2;

            for (int d = 0; d < 4; d++) {
                int nx = x + delta[d][0], ny = y + delta[d][1];

                if (nx < 0 || ny < 0 || nx >= size || ny >= size) continue;
                if (board[nx][ny] != 1) continue;
                // 지나간 자리 표시
                board[nx][ny] = -2;
                que.offer(new int[]{nx, ny, dist + 1});
            }
        }

        return -1;
    }
}