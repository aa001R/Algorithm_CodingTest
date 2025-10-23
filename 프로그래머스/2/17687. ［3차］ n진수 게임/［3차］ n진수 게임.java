import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder numStr = new StringBuilder();
        StringBuilder targetStr = new StringBuilder();
        int number = 0;
        while(numStr.length() < t * m){
           numStr.append(Integer.toString(number++, n));
        }
        for(int i = 0; i < t; i++){
            targetStr.append(numStr.charAt(p-1 + i*m)); // 0-base
        }
        return targetStr.toString().toUpperCase();
    }
}