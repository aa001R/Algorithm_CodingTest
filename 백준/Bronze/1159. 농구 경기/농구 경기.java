import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int [] alphabet = new int[26];
		for (int i = 0; i < n; i++) {
			String player = br.readLine();
			alphabet[player.charAt(0) - 'a']++;
		}
		boolean isPredaja = true;
		for (int i = 0; i < alphabet.length; i++) {
			if(alphabet[i] >= 5) {
				isPredaja = false;
				bw.append((char) (i + 'a'));
			}
		}
		if (isPredaja){
			bw.write("PREDAJA");
		}
		bw.flush();
	}
}
