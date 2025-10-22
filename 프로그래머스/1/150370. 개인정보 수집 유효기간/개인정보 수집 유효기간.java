import java.util.*;

class Solution {
    /*
    약관별 보관 유효기간 존재
    모든 달 28일까지
    */
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> termsMap = new HashMap<>();
        int todayD = toDays(today); // 오늘 날짜를 일 단위로 변환
        for(String term : terms){
            String[] info = term.split(" ");
            termsMap.put(info[0], Integer.parseInt(info[1]) * 28);
        }
        for(int i = 0; i < privacies.length; i++){
            String[] p = privacies[i].split(" ");
            String dateStr = p[0];
            String termType = p[1];
            int expirationD = toDays(dateStr) + termsMap.get(termType) - 1;
            if(todayD > expirationD) answer.add(i + 1);
        }
        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }
    
    private int toDays(String dateStr) {
        int[] d = Arrays.stream(dateStr.split("\\.")).mapToInt(Integer::parseInt).toArray();
        return d[0] * 12 * 28 + d[1] * 28 + d[2];
    }
}