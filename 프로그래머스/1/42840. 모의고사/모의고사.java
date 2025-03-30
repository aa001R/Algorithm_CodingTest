import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int [][] picks = new int[4][];
        picks[1] = new int[]{1, 2, 3, 4, 5};
        picks[2] = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        picks[3] = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int [] score = new int [4];
        for (int i = 0; i < answers.length; i++) {
            for (int j = 1; j <= 3; j++) {
                if (answers[i] == picks[j][i % picks[j].length]) score[j]++;                 
            }
        }
        
        // int maxScore = Math.max(score[1], Math.max(score[2], score[3]));
        int maxScore = Arrays.stream(score).max().getAsInt();
        ArrayList<Integer> list = new ArrayList<>();
        if(maxScore == score[1]) {list.add(1);}
        if(maxScore == score[2]) {list.add(2);}
        if(maxScore == score[3]) {list.add(3);}
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}