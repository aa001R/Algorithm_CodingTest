import java.util.*;
/*
t초 동안 붕대 감으면서 1초마다 +x 체력 회복
t초 연속 붕대 감으면 y 추가 회복
0 < 체력 <= 최대 체력
공격 당할 시 기술 초기화 & 체력 손상 & 당하는 순간은 회복 불가
=> 다시 붕대 감기
*/
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;
        int curTime = 0;
        for(int [] attack : attacks){
            // 공격 받기 전까지 회복
            int healT = (attack[0] - curTime);
            int heal = healT * bandage[1] + healT / bandage[0] * bandage[2];
            answer += heal;
            if(answer > health) answer = health;
            curTime = attack[0] + 1;
            // 공격받음
            answer -= attack[1];
            if(answer <= 0) return -1;
        }
        return answer;
    }
}