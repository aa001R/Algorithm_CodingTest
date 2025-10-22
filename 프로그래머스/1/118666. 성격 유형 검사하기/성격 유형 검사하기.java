import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        int[] score = {0, 3, 2, 1, 0, 1, 2, 3};
        Map<Character, Integer> map = new HashMap<>();
        for (char c : "RTCFJMAN".toCharArray()) map.put(c, 0);
        
        for (int i = 0; i < survey.length; i++) {
            char a = survey[i].charAt(0), b = survey[i].charAt(1);
            int choice = choices[i];
            if (choice < 4) map.put(a, map.get(a) + score[choice]);
            else if (choice > 4) map.put(b, map.get(b) + score[choice]);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(map.get('R') >= map.get('T') ? 'R' : 'T');
        sb.append(map.get('C') >= map.get('F') ? 'C' : 'F');
        sb.append(map.get('J') >= map.get('M') ? 'J' : 'M');
        sb.append(map.get('A') >= map.get('N') ? 'A' : 'N');
        return sb.toString();
    }
}
