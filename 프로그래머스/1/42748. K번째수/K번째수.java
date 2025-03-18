import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int cmdCnt = commands.length;
        int[] answer = new int[cmdCnt];
        for (int i = 0; i < cmdCnt; i++) {
            int [] arr = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);
            Arrays.sort(arr);
            answer[i] = arr[commands[i][2]-1];
        }
        return answer;
    }
}