import java.util.*;

class Solution {
    int n, m;
    boolean[][] visited;
    int[] cols;
    int[][] delta = {{-1, 0},{1, 0},{0, -1},{0, 1}};
    public int solution(int[][] land) {
        int answer = 0;
        n = land.length; m = land[0].length;
        visited = new boolean[n][m];
        cols = new int[m];
        for(int r = 0; r < n; r++){
            for(int c = 0; c < m; c++){
                if(visited[r][c] || land[r][c] == 0) continue;
                bfs(r, c, land);
            }
        }
        for(int col : cols){
            answer = Math.max(answer, col);
        }
        return answer;
    }
    
    private void bfs(int sr, int sc, int[][] land){
        Set<Integer> colIdx = new HashSet<>();
        colIdx.add(sc);
        ArrayDeque<int []> que = new ArrayDeque<>();
        que.offer(new int[]{sr, sc});
        visited[sr][sc] = true;
        int cnt = 1;
        while(!que.isEmpty()){
            int[] cur = que.poll();
            for(int d = 0; d < 4; d++){
                int nr = cur[0] + delta[d][0];
                int nc = cur[1] + delta[d][1];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                if(land[nr][nc] == 0) continue;
                colIdx.add(nc);
                que.offer(new int[] {nr, nc});
                cnt++;
            }
        }
        for(int col : colIdx){
            cols[col] += cnt;   
        }
    }    
}