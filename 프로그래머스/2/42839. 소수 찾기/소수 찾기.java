import java.util.*;

public class Solution {

    // 소수 판별 함수
    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // 모든 숫자 조합을 만들기 위한 백트래킹 함수
    public static void dfs(String prefix, String remaining, Set<Integer> numberSet) {
        if (!prefix.equals("")) {
            numberSet.add(Integer.parseInt(prefix)); // 만들어진 숫자를 저장
        }

        for (int i = 0; i < remaining.length(); i++) {
            // i번째 문자를 prefix에 추가하고, 나머지를 재귀적으로 전달
            dfs(prefix + remaining.charAt(i),
                remaining.substring(0, i) + remaining.substring(i + 1),
                numberSet);
        }
    }

    public static int solution(String numbers) {
        Set<Integer> numberSet = new HashSet<>();
        dfs("", numbers, numberSet);  // 순열 생성
        int count = 0;
        for (int num : numberSet) {
            if (isPrime(num)) count++;
        }
        return count;
    }
}
