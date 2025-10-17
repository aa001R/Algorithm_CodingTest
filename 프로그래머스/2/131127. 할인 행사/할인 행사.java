import java.util.*;

class Solution {
    /* 일정 금액 -> 10일 회원 -> 하루 한제품 할인
    매일 할인 제품 종류 다름
    할인 기간 10일동안 원하는 제품 할인 구매 
    => 가능한 회원 가입 날짜 갯수 계산
    */
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> wantIdx = new HashMap<>();
        for (int i = 0; i < want.length; i++){
            wantIdx.put(want[i], i); 
        }

        int isBuy = 1 << want.length;
        int isAll = (1 << (want.length + 1)) - 1;
        int[] buyCnt = new int[want.length];
        
        for (int i = 0; i < 10; i++) {
            int idx = wantIdx.getOrDefault(discount[i], want.length);
            if(idx == want.length) continue;
            if(++buyCnt[idx] >= number[idx]) isBuy |= (1 << idx);
            if(isBuy == isAll) answer++;
        }
        
        for (int i = 10; i < discount.length; i++){
            int nIdx = wantIdx.getOrDefault(discount[i], want.length);
            int preIdx = wantIdx.getOrDefault(discount[i-10], want.length);
            if(nIdx < want.length && ++buyCnt[nIdx] >= number[nIdx]) isBuy |= (1 << nIdx);
            if(preIdx < want.length && --buyCnt[preIdx] < number[preIdx]) isBuy &= ~(1 << preIdx);
            if(isBuy == isAll) answer++;
        }
        
        return answer;
    }
}