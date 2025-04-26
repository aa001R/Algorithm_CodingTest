import java.util.Arrays;

public class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        
        int n = citations.length;
        
        for (int i = 0; i < n; i++) {
            int h = n - i; // h = citations[i]번 이상 인용된 논문 수 (i번째 이후)
            if (citations[i] >= h) {
                return h;
            }
        }
        
        return 0;
    }
}
