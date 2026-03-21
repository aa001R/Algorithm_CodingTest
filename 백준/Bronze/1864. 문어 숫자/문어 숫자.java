import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String sign = "-\\(@?>&%";
		String input;
		while(!(input = br.readLine()).equals("#")){
			int count = sign.indexOf(input.charAt(0));
			for (int i = 1; i < input.length(); i++){
				count = count * 8 + sign.indexOf(input.charAt(i));
			}
			bw.append(Integer.toString(count)).append("\n");
		}
		bw.flush();
	}
}
