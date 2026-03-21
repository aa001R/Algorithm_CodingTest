import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input;
		HashMap<Character, Integer> vowelMap;
		while(!(input = br.readLine().toLowerCase()).equals("#")){
			vowelMap = makeMap();
			for (int i = 0; i < input.length(); i++){
				if (vowelMap.containsKey(input.charAt(i))){
					vowelMap.put(input.charAt(i), vowelMap.get(input.charAt(i))+1);
				}
			}
			int count = 0;
			for (int cnt : vowelMap.values()){
				count+= cnt;
			}
			bw.append(Integer.toString(count));
			bw.newLine();
		}
		bw.flush();
	}

	static HashMap<Character, Integer> makeMap() {
		return new HashMap<>(Map.of(
			'a', 0,
			'e', 0,
			'i', 0,
			'o', 0,
			'u', 0
		));
	}
}
