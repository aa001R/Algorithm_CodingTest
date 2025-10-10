import java.util.*;
class Solution {
    public int solution(int n, int k) {
        // 1. k진법으로 변환
        String baseK = Integer.toString(n, k);
        // System.out.println(Arrays.toString("00100200".split("0+")));
        // System.out.println(Arrays.toString("00100200".split("0+", -1)));
        // System.out.println(Arrays.toString("00100200".split("0")));
        // 2. 0을 기준으로 분리
        String[] parts = baseK.split("0+");
        
        int count = 0;
        for (String part : parts) {
            if (part.isEmpty()) continue; // 빈 문자열 무시
            long num = Long.parseLong(part);
            if (isPrime(num)) count++;
        }
        
        return count;
    }

    /** 소수 판별: √n 까지만 확인 */
    private boolean isPrime(long num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        long sqrt = (long)Math.sqrt(num);
        for (long i = 3; i <= sqrt; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
