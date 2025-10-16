class Solution {
    public int solution(int n) {
        // int answer = 1; // n = n
        /* n = a + (a+1) + (a+2) + ... + (a+i-1) : i개 연속 수
        n = i * (a+(a+i-1))/2 = i * (2a+i-1)/2
        a = n/i - (i-1)/2 >= 1        
        a = (n - i(i-1)/2) / i : a는 정수
        */
        int answer = 0;
        for (int i = 1; i <= n; i += 2) 
            if (n % i == 0) answer++;
        return answer;
    }
}