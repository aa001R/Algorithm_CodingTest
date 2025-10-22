import java.util.*;

class Solution {
    /* 
    mbti, 7개 대답별 점수
    RT, CF, JM, AN
    매우 3, 기본 2, 약간 1, 모르겠음 0
    결과 = 성격 유형 합산 더 높은 점수 > 사전순
    survey[i] = 비동의동의
    성격 유형 return
    */
    public String solution(String[] survey, int[] choices) {
        int n = survey.length;
        int[] scores = new int[8];
        String[] personality = {"RT", "CF", "JM", "AN"};
        Map<Character, Integer> total = new HashMap<>();
        for(int i = 1; i <= 3; i++) {
            scores[i] = scores[8 - i] = 4 - i;
        }
        for(String p : personality){
            total.put(p.charAt(0), 0);
            total.put(p.charAt(1), 0);
        }
        for(int i = 0; i < n; i++){
            char disagree = survey[i].charAt(0);
            char agree = survey[i].charAt(1);
            if(choices[i] < 4){ // 비동의
                total.put(disagree, total.get(disagree) + scores[choices[i]]);
            } else if(choices[i] > 4){ // 동의
                total.put(agree, total.get(agree) + scores[choices[i]]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String p : personality){
            char a = p.charAt(0);
            char b = p.charAt(1);
            if(total.get(a) >= total.get(b)) sb.append(a);
            else sb.append(b);
        }
        return sb.toString();
    }
}