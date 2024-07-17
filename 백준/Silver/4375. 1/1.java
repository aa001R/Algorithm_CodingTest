import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input;
		while((input = br.readLine()) != null && !input.isEmpty()) {
			int num = Integer.parseInt(input);
			int tmp = 0;
			for(int i = 1; i <= num; i++) {
				tmp = tmp * 10 + 1;
				if( (tmp%=num) != 0) continue;
				bw.append(Integer.toString(i)); bw.newLine();
				break;
			}
		}
		bw.flush();
	}
}