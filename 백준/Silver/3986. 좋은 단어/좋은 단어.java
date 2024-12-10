import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int count = 0;
		for (int i = 0; i < t; i++) {
			if (IsgoodWord(br.readLine())) count++;
		}
		System.out.print(count);
	}

	static boolean IsgoodWord(String s) {
		if (s.length() % 2 == 1) return false; //문자열의 길이가 홀수이다는 것은 A 또는 B의 개수가 홀수이므로 좋은단어 X
		ArrayDeque<Character> stack = new ArrayDeque<>();
		stack.push(s.charAt(0)); //첫 단어는 스택에 push
		for (int i = 1; i < s.length(); i++) {
			if ( !stack.isEmpty() && stack.peek() == s.charAt(i)) {
				stack.pop();
			} else {
				stack.push(s.charAt(i));
			}
		}
		if (stack.isEmpty()) return true;
		return false;
	}
}
