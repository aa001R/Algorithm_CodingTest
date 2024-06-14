import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception{
		String first = br.readLine(); 
		String second = br.readLine();
		String third = br.readLine();
		int nextNum = 0;
		StringBuilder sb = new StringBuilder();
		if(third.matches("\\d*")) {
			nextNum = Integer.parseInt(third) + 1;
		}else if(second.matches("\\d*")) {
			nextNum = Integer.parseInt(second) + 2;
		}else if(first.matches("\\d*")) {
			nextNum = Integer.parseInt(first) + 3;
		}
		if(nextNum % 3 == 0) {
			sb.append("Fizz");
		}
		if(nextNum % 5 == 0) {
			sb.append("Buzz");
		}
		if(nextNum % 3 != 0 && nextNum % 5 != 0) {
			sb.append(nextNum);
		}
		System.out.println(sb);
	}
}