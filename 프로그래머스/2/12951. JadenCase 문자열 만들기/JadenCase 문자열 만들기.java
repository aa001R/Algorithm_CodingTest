import java.util.*;

class Solution {
    // 대(첫문자) + 소(나머지문자)
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean isStart = true;
        for (char c : s.toCharArray()){
            if (c == ' '){
                isStart = true;
            } else if (isStart) {
                isStart = false;
                c = Character.toUpperCase(c);
            } else {
                c = Character.toLowerCase(c);
            }
            sb.append(c);
        }
        return sb.toString();
    }
}