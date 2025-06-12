import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        boolean [] isReserve = new boolean[n+2];
        Arrays.sort(lost);
        for (int idx : reserve){
            isReserve[idx] = true;
        }
        for(int i = 0; i < lost.length; i++){
            if (isReserve[lost[i]]) {
                isReserve[lost[i]] = false;
                lost[i] = 0;
                answer++;
            }
        }
        for(int lostStu : lost){
            if (lostStu == 0) continue;
            else if (isReserve[lostStu - 1]) isReserve[lostStu - 1] = false;
            else if (isReserve[lostStu+1]) isReserve[lostStu + 1] = false;
            else continue;
            answer++;
        }
        return answer;
    }

}