import java.util.*;

class Solution {
    /*
    약관별 보관 유효기간 존재
    모든 달 28일까지
    */
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> termsMap = new HashMap<>();
        // 월을 일 기준으로 변경
        int[] date = Arrays.stream(today.split("\\.")).mapToInt(Integer::parseInt).toArray();
        int todayD =  date[0] * 12 * 28 + date[1] * 28 + date[2];
        for(String term : terms){
            String[] info = term.split(" ");
            termsMap.put(info[0], Integer.parseInt(info[1])*28);
        }
        for(int i = 0; i < privacies.length; i++){
            String[] p = privacies[i].split(" ");
            date = Arrays.stream(p[0].split("\\.")).mapToInt(Integer::parseInt).toArray();
            int expirationD = date[0] * 12 * 28 + date[1] * 28 + date[2] + termsMap.get(p[1]) - 1;
            if(todayD > expirationD) answer.add(i+1);
        }
        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }
}