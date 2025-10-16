import java.util.*;

class Solution {
    HashMap<Integer, Integer> fibo = new HashMap<>();
    int MOD = 1234567;

    public int solution(int n) {
        fibo.put(0, 0); fibo.put(1, 1);
        return Fibo(n);
    }

    private int Fibo(int n) {
        if (fibo.containsKey(n)) return fibo.get(n);
        if (n % 2 == 0) {
            fibo.put(n, (int)(( (long)Fibo(n / 2) * ((2L * Fibo(n / 2 - 1) + Fibo(n / 2)) % MOD) ) % MOD));
        } else {
            fibo.put(n, (int)(( (long)Fibo(n / 2) * Fibo(n / 2) % MOD + (long)Fibo(n / 2 + 1) * Fibo(n / 2 + 1) % MOD ) % MOD));
        }
        return fibo.get(n);
    }
}
