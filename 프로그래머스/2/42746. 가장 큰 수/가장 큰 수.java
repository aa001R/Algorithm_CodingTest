import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        
        String [] strNumbers = Arrays.stream(numbers)
                                    .mapToObj(String::valueOf)
                                    .toArray(String[]::new);
        
        Arrays.sort(strNumbers, (a, b) -> (b+a).compareTo(a+b));
        
        if (strNumbers[0].equals("0")) return "0";

        for (String str:strNumbers){
            sb.append(str);
        }
        return sb.toString();
    }
}