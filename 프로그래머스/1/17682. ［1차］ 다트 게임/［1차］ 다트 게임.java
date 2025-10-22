import java.util.*;

class Solution {
    /* 3회, 0-10점, S*1, D*2, T*3
    * 직전+해당 점수 2배 (첫번째 기회에서 나올경우 해당점수만*2) 중첩 가능
    # 해당 점수 마이너스 (*과 중첩 가능 => *-2)
    */
    public int solution(String dartResult) {        
        int answer = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int num = dartResult.charAt(0) - '0';
        stack.push(num);
        for(int i = 1; i < dartResult.length(); i++){
            char c = dartResult.charAt(i);
            if(Character.isDigit(c)) {
                num = c - '0';
                if (Character.isDigit(dartResult.charAt(i-1))){
                    num = stack.pop() * 10 + num;
                }
            }
            else {
                num = stack.pop();
                if(c == 'D') {
                    num *= num;
                }
                else if(c == 'T') {
                    num *= num * num;
                }
                else if(c == '#') {
                    num *= (-1);
                }
                else if(c == '*') {
                    if (!stack.isEmpty()) {
                        int pre = stack.pop();
                        stack.push(pre * 2);
                    }
                    num *= 2;
                }
            }
            stack.push(num);
        }
        while(!stack.isEmpty()){
            answer += stack.pop();
        }
        return answer;
    }
}