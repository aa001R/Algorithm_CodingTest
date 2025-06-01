import java.util.*;
class Solution {
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        answer = perm(k, 0, dungeons, new boolean[dungeons.length], 0);
        return answer;
    }
    
    private int perm(int curFatigue, int select, int [][] dungeons, boolean [] visited, int cnt) {
        int max = cnt;
        if (select >= dungeons.length) {
            return cnt;
        }
        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) continue;
            if (curFatigue < dungeons[i][0]) continue;
            visited[i] = true;
            max = Math.max(perm(curFatigue - dungeons[i][1], select+1, dungeons, visited, cnt+1), max);
            visited[i] = false;
        }
        return max;
    }
}