import java.util.*;

class Solution {
    /*
    2*2 모양이면 터짐
    지워진 후 아래로 빈공간 채움
    지우고 > 채우고 반복
    지워진 블록 갯수 반환
    */
    int m, n;
    public int solution(int m, int n, String[] board) {
        this.m = m;
        this.n = n;
        int answer = 0;
        char[][] grid = new char[m][n];
        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){
                grid[r][c] = board[r].charAt(c);
            }
        }
        while(checkBoom(grid)){ // 터지는지 확인
           move(grid); // 터진 후 이동
        }
        for(int c = 0; c < n; c++){
            for(int r = 0; r < m; r++){
                if(!Character.isDigit(grid[r][c])) break;
                answer++;
            }
        }
        return answer;
    }
    
    private boolean checkBoom(char[][] board){
        char[][] tmp = copyBoard(board);
        boolean isBoom = false;
        for(int r = 0; r < m - 1; r++){
            for(int c = 0; c < n - 1; c++){
                if(tmp[r][c] == '0' || tmp[r][c] != tmp[r][c+1] || tmp[r][c] != tmp[r+1][c+1] || tmp[r][c] != tmp[r+1][c]) continue;
                board[r][c] = board[r+1][c] = board[r][c+1] = board[r+1][c+1] = '0';
                isBoom = true;
            }
        }
        return isBoom;
    }
    
    private void move(char[][] board){
        for(int c = 0; c < n; c++){
            int curR = m-1, nextR = curR - 1;
            while(curR >= 0 && nextR >= 0){
                while(curR >= 0 && board[curR][c] != '0') curR--;
                while(nextR > curR || nextR >= 0 && board[nextR][c] == '0') nextR--;
                if (curR < 0 || nextR < 0) break;
                board[curR--][c] = board[nextR][c];
                board[nextR--][c] = '0';
            }            
        }
    }
    
    private char[][] copyBoard(char[][] original){
        char[][] copy = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }
}