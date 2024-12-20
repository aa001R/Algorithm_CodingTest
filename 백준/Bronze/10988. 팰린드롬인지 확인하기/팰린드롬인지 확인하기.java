import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		boolean isPalindrome = true;
		for(int i = 0; i < input.length() / 2; i++){
			if(input.charAt(i) != input.charAt((input.length()-1)-i)){
				isPalindrome = false;
				break;
			}
		}
		if(isPalindrome) {
			System.out.println(1);
		}else{
			System.out.println(0);
		}
	}
}
