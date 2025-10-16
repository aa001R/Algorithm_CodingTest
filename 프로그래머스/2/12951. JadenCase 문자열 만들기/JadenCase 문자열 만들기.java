import java.util.*;

class Solution {
    // 대(첫문자) + 소(나머지문자)
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean isStart = true;
        for (char c : s.toLowerCase().toCharArray()){
            sb.append(isStart ? Character.toUpperCase(c) : c);
            isStart = c == ' ';
        }
        return sb.toString();
    }
}