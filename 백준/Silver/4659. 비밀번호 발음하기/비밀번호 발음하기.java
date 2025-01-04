import java.io.*;

public class Main {
	static int len;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input;
		while(!(input = br.readLine()).equals("end")){
			len = input.length();
			boolean isIncludeVowel = checkVowel(input.charAt(len-1)), isAcceptable = true;
			for(int i = 0; i < len - 1 && isAcceptable; i++){
				char cur = input.charAt(i);
				if ( cur == input.charAt(i + 1) && cur != 'e' && cur != 'o' ){
					isAcceptable = false;
					break;
				}
				if (checkVowel(cur)){
					isIncludeVowel = true;
					if (isIn(i+2) && checkVowel(input.charAt(i + 1), input.charAt(i + 2))) {
						isAcceptable = false;
						break;
					}
				}else {
					if (isIn(i+2) && !checkVowel(input.charAt(i + 1)) && !checkVowel(input.charAt(i + 2))) {
						isAcceptable = false;
						break;
					}
				}
			}
			bw.append("<").append(input).append("> is ");
			if(!isIncludeVowel || !isAcceptable){
				bw.append("not ");
			}
			bw.append("acceptable.\n");
		}
		bw.flush();
	}

	static boolean isIn(int c){
		return c < len;
	}

	static boolean checkVowel(char... cur){
		for (char c : cur) {
			switch (c) {
				case 'a': case'e': case 'i': case 'o': case 'u':
					continue;
				default:
					return false;
			}
		}
		return true;
	}
}
