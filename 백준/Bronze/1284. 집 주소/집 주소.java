import java.io.*;
import java.util.*;

public class Main {
	static int [] margin = {4, 2, 3, 3, 3, 3, 3, 3, 3, 3};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input;
		while(!(input = br.readLine()).equals("0")){
			int count = 1;
			for (int i = 0; i < input.length(); i++){
				count += margin[input.charAt(i)-'0'] + 1;
			}
			bw.append(Integer.toString(count)).append("\n");
		}
		bw.flush();
	}
}
