import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String [] keyboard = {
			"1QAZ","2WSX","3EDC","4RFV5TGB",
			"6YHN7UJM","8IK,","9OL.","0P;/-[\'=]"
		};
		int [] count = new int[keyboard.length];
		String input = br.readLine();
		for (int i = 0; i < input.length(); i++) {
			for (int j = 0; j < keyboard.length; j++) {
				if (keyboard[j].contains(Character.toString(input.charAt(i)))) {
					count[j]++;
					break;
				}
			}
		}
		for (int c : count){
			bw.append(Integer.toString(c)).append("\n");
		}
		bw.flush();
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if(isNegative) n = System.in.read() & 15;
		while((cur = System.in.read()) > 32) n = (n << 3) + (n << 1) + (cur & 15);
		return isNegative? -n : n;
	}
}
