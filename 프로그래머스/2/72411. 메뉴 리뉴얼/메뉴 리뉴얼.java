import java.util.*;

class Solution {
    
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        
        for (int c : course) { // c개 요소 조합
            Map<String, Integer> comboCount = new HashMap<>();
            
            // 주문에서 조합 생성 및 카운팅
            for (String order : orders) {
                char[] chars = order.toCharArray();
                Arrays.sort(chars); // 알파벳 순서 정렬
                List<String> combos = new ArrayList<>();
                combine(chars, c, 0, "", combos); // 원소, 조합원소수, 현재 idx, selected, 조합 list
                
                for (String comb : combos) {
                    comboCount.put(comb, comboCount.getOrDefault(comb, 0) + 1);
                }
            }
            
            // 최대 빈도 찾기
            int maxCount = 0;
            for (int cnt : comboCount.values()) {
                if (cnt > maxCount) maxCount = cnt;
            }
            
            // 최대 빈도 >= 2인 조합만 후보
            for (String comb : comboCount.keySet()) {
                if (comboCount.get(comb) == maxCount && maxCount >= 2) answer.add(comb);
            }
        }
        
        Collections.sort(answer); // 사전순 정렬
        return answer.toArray(new String[0]);
    }
    
    // 조합 생성 재귀 함수
    private void combine(char[] arr, int length, int start, String path, List<String> res) {
        if (path.length() == length) {
            res.add(path);
            return;
        }
        for (int i = start; i < arr.length; i++) {
            combine(arr, length, i + 1, path + arr[i], res);
        }
    }
}
