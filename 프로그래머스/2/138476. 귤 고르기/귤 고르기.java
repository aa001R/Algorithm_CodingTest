import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        for (int t : tangerine){
            cntMap.put(t, cntMap.getOrDefault(t, 0)+1);
        }
        List<Integer> cntList = new ArrayList<>(cntMap.values());
        cntList.sort(Comparator.reverseOrder());
        int answer = 0;
        while((k -= cntList.get(answer++)) > 0);
        return answer;
    }
}