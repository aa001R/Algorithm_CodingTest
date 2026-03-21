import java.io.*;
import java.util.*;

public class Main {
	static Set<Character> candidate = new HashSet<>(Arrays.asList('I', 'O', 'S', 'H', 'Z', 'X', 'N'));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		for (int i = 0; i < input.length(); i++) {
			if (!candidate.contains(input.charAt(i))) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
}
