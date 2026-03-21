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
		return new HashMap<Character, Integer>(){{
			put('a', 0);
			put('e', 0);
			put('i', 0);
			put('o', 0);
			put('u', 0);
		}};
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if(isNegative) n = System.in.read() & 15;
		while((cur = System.in.read()) > 32) n = (n << 3) + (n << 1) + (cur & 15);
		return isNegative? -n : n;
	}
}
