import java.util.*;

class Solution {
    /*
    같은 인형 연속 2개 > 사라짐
    바구니 : 모든 인형이 들어갈 수 있는 크기
    터트러져 사라진 인형 갯수 return
    */
    public int solution(int[][] board, int[] moves) {
        int popCnt = 0;
        int n = board.length;
        Deque<Integer> stack = new ArrayDeque<>(); // bucket
        for (int col : moves) { // col 1-base
            int row = 0;
            while(row < n && board[row][col-1] == 0) row++;
            if (row >= n) continue;
            int doll = board[row][col-1];
            if(!stack.isEmpty() && stack.peek() == doll) {
                stack.pop();
                popCnt += 2;
            }
            else {
                stack.push(doll);
            }
            board[row][col-1] = 0;
        }
        
        return popCnt;
    }
}