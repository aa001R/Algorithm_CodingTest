import java.util.*;

class Solution {
    // DFS / 조합 / 중복제거 {{a,b,c} {a,b}}
    Set<BitSet> answer; // Set<Set<String>>
    public int solution(String[] user_id, String[] banned_id) {
        answer = new HashSet<>();
        dfs(new BitSet(), 0, user_id, banned_id);
        return answer.size();
    }
    
    private void dfs(BitSet isSelect, int idx, String[] userId, String[] bannedId){
        if(idx >= bannedId.length){
            answer.add(isSelect);
            return;
        }
        for(int i = 0; i < userId.length; i++){
            if(isSelect.get(i)) continue;
            if(isMatch(userId[i], bannedId[idx])){
                isSelect.set(i);
                dfs(isSelect, idx+1, userId, bannedId);
                isSelect.clear(i);
            }
        }
    }
    
    private boolean isMatch(String userId, String bannedId){
        if(userId.length() != bannedId.length()) return false;
        for(int i = 0; i < userId.length(); i++){
            if(bannedId.charAt(i) == '*') continue;
            if(bannedId.charAt(i) != userId.charAt(i)) return false;
        }
        return true;
    }
}