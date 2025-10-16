class Solution {
    public int solution(int n) {
        int answer = 1; // n = n
        /* n = a + (a+1) + (a+2) + ... + (a+i-1) : i개 연속 수
        n = i * (a+(a+i-1))/2 = i * (2a+i-1)/2
        a = n/i - (i-1)/2 >= 1        
        a = (n - i(i-1)/2) / i : a는 정수
        */
        for (int len = 2; len * (len + 1) / 2 <= n; len++) {
            if ((n - len * (len - 1) / 2) % len == 0) {
                answer++;
            }
        }
        return answer;
    }
}