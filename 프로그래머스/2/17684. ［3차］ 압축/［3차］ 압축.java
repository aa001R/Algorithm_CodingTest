import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> dict = new HashMap<>();
        for(int i = 0; i < 26; i++){
            dict.put(String.valueOf((char)('A'+i)), i+1);
        }
        int cur = 0, next = 0;
        while(cur < msg.length()){
            while(next < msg.length() && dict.containsKey(msg.substring(cur, next+1))){ next++; }
            String w = msg.substring(cur, next);
            answer.add(dict.get(w));
            if(next < msg.length()) dict.put(w+msg.charAt(next), dict.size()+1);
            cur = next;
        }
        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }
}