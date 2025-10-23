import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int order = 0, number = 0;
        while(sb.length() < t){
           for(String num : Integer.toString(number, n).split("")){
               if(order++ % m + 1 == p) sb.append(num);
               if(sb.length() >= t) break;
           }
            number++;
        }
        return sb.toString().toUpperCase();
    }
}