import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int [2];
        int total = brown + yellow;
        for (int h = 2; h <= Math.sqrt(total); h++) {
            if (total % h != 0) continue;
            int w = total / h;
            int border = w * 2 + h * 2 - 4;
            if (border != brown) continue;
            int center = total - border;
            if (center != yellow) continue;
            answer[0] = w; answer[1] = h;
            break;
        }
        return answer;
    }
}