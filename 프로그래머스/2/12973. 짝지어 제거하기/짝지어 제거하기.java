import java.util.*;

class Solution
{
    /*
    같은 알파벳 2개 붙어 있는 짝 탐색 -> 제거 -> 반복
    문자열 모두 제거 성공 : 1 실패 : 0 
    // 정규식 -> 시간초과 (매번 새로 검사)
    // 스택 -> O(n)
    */
    public int solution(String s)
    {
        if (s.length() % 2 == 1) return 0;
        char[] stack = new char[s.length()];
        int top = -1;
        for (char c : s.toCharArray()){
            if (top >= 0 && stack[top] == c){
                top--;
            }
            else {
                stack[++top] = c;
            }
        }
        return top == -1 ? 1 : 0;
    }
}