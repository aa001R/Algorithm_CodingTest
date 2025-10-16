import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static HashMap<Long, Long> fibo;
	static final long MOD = 1_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		fibo = new HashMap<>();
		fibo.put(0L, 0L);
		fibo.put(1L, 1L);
		fibo.put(2L, 1L);
		for (long i = 3; i < 6; i++) {
			fibo.put(i, (fibo.get(i - 1) + fibo.get(i - 2)) % MOD);
		}
		long num = Long.parseLong(st.nextToken());
		System.out.println(Fibo(num)%MOD);
	}

	static long Fibo(long i) {
		if (fibo.containsKey(i))
			return fibo.get(i);

		if (i % 2 == 0) {
			fibo.put(i, (Fibo(i / 2) * (((2 * Fibo(i / 2 - 1)) % MOD + Fibo(i / 2)) % MOD)) % MOD);
		} else {
			fibo.put(i, ((Fibo(i / 2 + 1) * Fibo(i/2+1)) % MOD + (Fibo(i/2) * Fibo(i/2))%MOD) % MOD);
		}
		
		return fibo.get(i);
	}
}