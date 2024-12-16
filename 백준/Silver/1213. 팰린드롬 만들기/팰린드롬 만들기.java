import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringBuilder answer = new StringBuilder();

		String str = br.readLine();
		int[] arr = new int[26];
		for (int i = 0; i < str.length(); i++) {
			arr[str.charAt(i) - 'A']++;
		}
		int odd = 0;
		int oddNum = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 != 0) {
				odd++;
				oddNum = i;
			}
			if (odd >= 2) {
				System.out.println("I'm Sorry Hansoo");
				return;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			sb.append(String.valueOf((char)(i + 'A')).repeat(arr[i] / 2));
		}
		answer.append(sb);
		if (odd == 1) {
			answer.append((char)(oddNum + 'A'));
		}
		answer.append(sb.reverse());
		System.out.println(answer);

	}
}
