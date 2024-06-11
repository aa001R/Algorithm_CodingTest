import java.io.*;
import java.util.*;

public class Main {
	static int N, K, max;
	static String[] words;
	static boolean[] learn;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		words = new String[N];
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			words[i] = word.substring(4, word.length() - 4);
		}
		if (K < 5) {
			bw.write("0");
		} else if (K == 26) {
			bw.write(Integer.toString(N));
		} else {
			learn = new boolean[26];
			learn['a' - 'a'] = true;
			learn['n' - 'a'] = true;
			learn['t' - 'a'] = true;
			learn['i' - 'a'] = true;
			learn['c' - 'a'] = true;
			max = Integer.MIN_VALUE;
			learnComb(0, 0);
			bw.write(Integer.toString(max));
		}
		bw.flush();
	}

	static void learnComb(int startIdx, int select) {
		if (select == K - 5) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				boolean read = true;
				for (int w = 0; w < words[i].length(); w++) {
					if (!learn[words[i].charAt(w) - 'a']) {
						read = false;
						break;
					}
				}
				if(read) cnt++;
			}
			max = Math.max(max, cnt);
			return;
		}
		for (int i = startIdx; i < 26; i++) {
			if (learn[i])
				continue;
			learn[i] = true;
			learnComb(i + 1, select + 1);
			learn[i] = false;
		}
	}
}
