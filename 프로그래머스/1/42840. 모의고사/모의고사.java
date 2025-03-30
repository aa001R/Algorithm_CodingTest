import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        ArrayList<Integer> answer = new ArrayList<>();
        int [][] picks = new int[4][];
        picks[1] = new int[]{1, 2, 3, 4, 5};
        picks[2] = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        picks[3] = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int [] correct = new int [4];
        for (int i = 0; i < answers.length; i++) {
            for (int j = 1; j <= 3; j++) {
                if (answers[i] == picks[j][i % picks[j].length]) correct[j]++;                 
            }
        }
        int max = correct[1]; answer.add(1);
        for (int i = 2; i <= 3; i++) {
            if (max < correct[i]) {
                max = correct[i];
                answer = new ArrayList<>();
                answer.add(i);
            } else if (max == correct[i]){
                answer.add(i);
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}