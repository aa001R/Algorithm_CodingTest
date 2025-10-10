import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> map1 = buildMultiSet(str1);
        Map<String, Integer> map2 = buildMultiSet(str2);

        int intersection = 0;
        int union = 0;

        // 교집합 계산
        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                intersection += Math.min(map1.get(key), map2.get(key));
            }
        }

        // 합집합 계산
        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (String key : allKeys) {
            int count1 = map1.getOrDefault(key, 0);
            int count2 = map2.getOrDefault(key, 0);
            union += Math.max(count1, count2);
        }

        // 공집합 처리
        if (union == 0) return 65536;

        double jaccard = (double) intersection / union;
        return (int) (jaccard * 65536);
    }

    // 두 글자씩 끊어 다중집합 Map 생성
    private Map<String, Integer> buildMultiSet(String s) {
        Map<String, Integer> map = new HashMap<>();
        s = s.toLowerCase();
        for (int i = 0; i < s.length() - 1; i++) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(i + 1);
            if (Character.isLetter(c1) && Character.isLetter(c2)) {
                String key = "" + c1 + c2;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        return map;
    }
}
