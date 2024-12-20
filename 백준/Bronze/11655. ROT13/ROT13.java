import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (Character.isUpperCase(c)) {
				bw.append((char) ((c - 'A' + 13) % 26 + 'A')); // 대문자 순환
			} else if (Character.isLowerCase(c)) {
				bw.append((char) ((c - 'a' + 13) % 26 + 'a')); // 소문자 순환
			} else {
				bw.append(c);
			}
		}
		bw.flush();
	}
}
