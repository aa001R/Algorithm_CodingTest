import java.util.*;
import java.io.*;

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException
	{
		String str = br.readLine();
		String bomb = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < str.length(); i++) {
			stack.push(str.charAt(i));
			if(stack.size() < bomb.length()) continue;
			boolean explode = true;
			for(int check = 0; check < bomb.length(); check++) {
				if(stack.get(stack.size()-1-check) != bomb.charAt(bomb.length()-1-check)) {
					explode = false;
					break;
				}
			}
			if(explode) {
				for(int j=0; j<bomb.length(); j++) {
					stack.pop();
				}
			}
		}
		if(stack.size() > 0) {
			for(char c : stack) {
				bw.append(Character.toString(c));
			}
		}else {
			bw.append("FRULA");
		}
		
		bw.flush();
	}
}