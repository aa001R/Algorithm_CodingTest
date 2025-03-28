import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> hm = new HashMap<>();
        for (String player : participant) hm.put(player, hm.getOrDefault(player, 0) + 1);
        for (String player : completion) hm.put(player, hm.get(player) - 1);
        for (Map.Entry<String, Integer> player : hm.entrySet()){
            if (player.getValue() == 0) continue; 
            answer = player.getKey();
            break;
        }
        return answer;
    }
}