import java.util.*;

class Solution {
    static int answer = 0;
    public int solution(int[] numbers, int target) {
        bfs(numbers, 0, target, new boolean [numbers.length]);
        return answer;
    }
    
    public void bfs(int[] numbers, int idx, int target, boolean [] minus){
        if (idx >= numbers.length){
            int sum = 0;
            for (int i = 0; i < numbers.length; i++){
                if (minus[i]) sum -= numbers[i];
                else sum += numbers[i];
            }
            if (target == sum) answer++;
            return ;
        }
        minus[idx] = true;
        bfs (numbers, idx+1, target, minus);
        minus[idx] = false;
        bfs (numbers, idx+1, target, minus);
    }
}