import java.util.*;
import java.io.*;

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException
	{
		String str = br.readLine();
		String bomb = br.readLine();
		
		char[] stack = new char[str.length()];
		int explodedSize = 0;
		char lastBombChar = bomb.charAt(bomb.length() - 1);
		for(int curIdx = 0; curIdx < str.length(); curIdx++) {
			stack[curIdx - explodedSize] = str.charAt(curIdx);
			if(str.charAt(curIdx) != lastBombChar) continue;
			boolean exploded = true;
			for(int check = 0; check < bomb.length() - 1; check++ ) {
				if(curIdx - explodedSize - check - 1 < 0 
					|| stack[curIdx - explodedSize - check - 1] != bomb.charAt(bomb.length() - 1 - check - 1)) {
					exploded = false;
					break;
				}
			}
			if(!exploded) continue;
			explodedSize += bomb.length();
		}
		if(explodedSize == str.length()) {
			bw.append("FRULA");
		}else {
			bw.append(new String(stack, 0, str.length() - explodedSize));
		}
		bw.flush();
	}
}