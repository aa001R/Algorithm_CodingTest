import java.util.*;

class Solution {
    static int n, m;
    public int solution(int[][] maps) {
        int answer = 0;
        n = maps.length;
        m = maps[0].length;
        int [][] walks = new int[n][m];
        int [][] delta = {{-1, 0},{1, 0},{0, 1},{0, -1}};
        ArrayDeque<int []> que = new ArrayDeque<>();
        walks[0][0] = 1;
        que.offer(new int[]{0, 0});
        
        while(!que.isEmpty()){
            int [] cur = que.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + delta[d][0];
                int nc = cur[1] + delta[d][1];
                if (isOut(nr, nc) || maps[nr][nc] == 0 || walks[nr][nc] != 0) continue;
                walks[nr][nc] = walks[cur[0]][cur[1]] + 1;
                que.offer(new int[] {nr, nc});
            }
        }
                
        if(walks[n-1][m-1] != 0) {
            answer = walks[n-1][m-1];
        } else {
            answer = -1;
        }
        return answer;
    }
    
    public boolean isOut(int r, int c){
        return r < 0 || r >= n || c < 0 || c >= m;
    }
}