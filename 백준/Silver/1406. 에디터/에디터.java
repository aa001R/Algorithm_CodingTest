import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		ArrayDeque<Character> left = new ArrayDeque<>();
		ArrayDeque<Character> right = new ArrayDeque<>();

		for (char c : br.readLine().toCharArray()) {
			left.offer(c);
		}
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			String line = br.readLine();
			char cmd = line.charAt(0);
			if (cmd == 'L') {
				if (!left.isEmpty()) right.push(left.removeLast());
			} else if (cmd == 'D') {
				if (!right.isEmpty()) left.offer(right.removeFirst());
			} else if (cmd == 'B') {
				if (!left.isEmpty()) left.removeLast();
			} else if (cmd == 'P') {
				left.offer(line.charAt(2));
			}
		}

		for (char c : left) sb.append(c);
		for (char c : right) sb.append(c);
		System.out.println(sb);
	}
}
