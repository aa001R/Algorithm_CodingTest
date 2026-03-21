import java.io.*;
import java.util.*;

public class Main {
	static String[] name = {"", "Yakk", "Doh", "Seh", "Ghar", "Bang", "Sheesh", "Habb Yakk", "Dobara", "Dousa", "Dorgy", "Dabash", "Dosh"};
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int [] dice = new int[2];
		int T = read();
		for (int t = 1; t <= T; t++) {
			bw.append("Case ").append(Integer.toString(t)).append(": ");
			dice[0] = read();
			dice[1] = read();
			if (dice[0]+dice[1] == 11) bw.append("Sheesh Beesh");
			else if (dice[0] == dice[1]) bw.append(name[dice[0]+6]);
			else {
				Arrays.sort(dice);
				bw.append(name[dice[1]]).append(" ").append(name[dice[0]]);
			}
			bw.newLine();
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
