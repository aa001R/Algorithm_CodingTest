import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = read(), K = read();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) a[i] = read();

        long count = 0;
        long prefixSum = 0;
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1); // 누적합이 정확히 K일 경우를 위한 초기값

        for (int i = 0; i < N; i++) {
            prefixSum += a[i];
            count += map.getOrDefault(prefixSum - K, 0);
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        System.out.println(count);
    }

    public static int read() throws IOException {
        int cur, n = System.in.read() & 15;
        boolean isNegative = (n == 13);
        if (isNegative) n = System.in.read() & 15;
        while ((cur = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (cur & 15);
        }
        return isNegative ? -n : n;
    }
}
