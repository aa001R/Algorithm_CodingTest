import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		int K = read();  // 입력값 K
		System.out.println(findKthLuckyNumber(K));  // 결과 출력
	}

	public static String findKthLuckyNumber(long K) {
		int length = 1;
		long count = 0;

		// Step 1: 자리수 결정
		while (true) {
			long numAtLength = 1L << length; // 2^length
			if (count + numAtLength >= K) break;
			count += numAtLength;
			length++;
		}
		
		// 4, 7 / 44, 47, 74, 77 / 444, 447, 474, 477, 744
		// 0, 1 / 00, 01, 10, 11 / 000, 001, 010, 011, 100 
		// Step 2: 해당 자리수에서 몇 번째인지 (0-based index)
		long idx = K - count - 1;

		// Step 3: 2진수로 변환 후 자릿수 맞추기 (앞에 0 채움)
		StringBuilder binary = new StringBuilder(Long.toBinaryString(idx));
		while (binary.length() < length) {
			binary.insert(0, '0');
		}

		// Step 4: 0 -> 4, 1 -> 7로 매핑
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < binary.length(); i++) {
			result.append(binary.charAt(i) == '0' ? '4' : '7');
		}

		return result.toString();
	}

	// 빠른 입력 처리
	public static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
