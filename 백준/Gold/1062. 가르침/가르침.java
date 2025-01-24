import java.util.*;
import java.io.*;

public class Main {
	static int N, K, max;
	static int[] words;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read();
		K = read();
		words = new int[N];
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			int len = word.length();
			for (int j = 4; j < len - 4; j++) {
				int charIndex = word.charAt(j) - 'a';
				words[i] |= (1 << charIndex);
			}
		}
		if (K < 5) {
			bw.write("0");
		} else if (K == 26) {
			bw.write(Integer.toString(N));
		} else {
			int learn = 0;
			learn |= 1; // a
			learn |= 1 << ('n' - 'a');
			learn |= 1 << ('t' - 'a');
			learn |= 1 << ('i' - 'a');
			learn |= 1 << ('c' - 'a');
			max = 0;
			learnComb(0, 0, learn);
			bw.write(Integer.toString(max));
		}
		bw.flush();
	}

	static void learnComb(int startIdx, int select, int learn) {
		if (select == K-5) {
			int cnt = 0;
			for (int word : words) {
				if ((word & learn) == word) { cnt++; }
			}
			max = Math.max(max, cnt);
			return;
		}

		for (int i = startIdx; i < 26; i++) {
			if ((learn & (1 << i)) != 0) continue;
			learnComb(i + 1, select + 1, learn | (1 << i));
		}
	}

	static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
