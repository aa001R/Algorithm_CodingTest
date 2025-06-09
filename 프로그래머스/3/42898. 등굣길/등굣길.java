import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int [][] board = new int[n+1][m+1];
        for (int [] puddle : puddles) {
            board[puddle[1]][puddle[0]] = -1;
        }
        board[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (board[i][j] < 0) {board[i][j] = 0; continue;}
                board[i][j] += (board[i-1][j] + board[i][j-1]) % 1_000_000_007;
            }
        }
        return board[n][m];
    }
}