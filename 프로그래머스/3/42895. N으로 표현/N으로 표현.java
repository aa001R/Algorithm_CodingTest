import java.util.*;

public class Solution {
    public int solution(int N, int number) {
        if (N == number) return 1;
        
        List<Set<Integer>> dp = new ArrayList<>();
        
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }
        
        dp.get(1).add(N);
        
        for (int i = 2; i <= 8; i++) {
            Set<Integer> current = dp.get(i);
            
            // 이어붙이기 (예: 5, 55, 555)
            int concatNumber = Integer.parseInt(String.valueOf(N).repeat(i));
            current.add(concatNumber);
            
            for (int j = 1; j < i; j++) {
                Set<Integer> set1 = dp.get(j);
                Set<Integer> set2 = dp.get(i - j);
                
                for (int a : set1) {
                    for (int b : set2) {
                        current.add(a + b);
                        current.add(a - b);
                        current.add(a * b);
                        if (b != 0) {
                            current.add(a / b); // 나누기(정수 나누기)
                        }
                    }
                }
            }
            
            if (current.contains(number)) {
                return i;
            }
        }
        
        return -1;
    }
}
