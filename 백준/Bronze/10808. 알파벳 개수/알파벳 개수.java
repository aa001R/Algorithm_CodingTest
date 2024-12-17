import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = br.readLine();
		int [] alphabet = new int[26];
		for (int i = 0; i < input.length(); i++){
			alphabet[input.charAt(i) - 'a']++;
		}
		for(int i = 0; i < 26; i++){
			sb.append(Integer.toString(alphabet[i])).append(" ");
		}
		System.out.println(sb);
	}
}
