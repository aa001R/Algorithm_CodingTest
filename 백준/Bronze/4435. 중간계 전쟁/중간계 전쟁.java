import java.io.*;
import java.util.*;

public class Main {
	static int [][] warriors = {
		{1, 2, 3, 3, 4, 10},
		{1, 2, 2, 2, 3, 5, 10}
	};
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = read();
		int goodSum = 0, evilSum = 0;
		for (int t = 1; t <= T; t++){
			bw.append("Battle ").append(Integer.toString(t)).append(": ");
			goodSum = 0;
			for (int i = 0; i < warriors[0].length; i++){
				goodSum += read() * warriors[0][i];
			}
			evilSum = 0;
			for (int i = 0; i < warriors[1].length; i++){
				evilSum += read() * warriors[1][i];
			}
			if (goodSum > evilSum) bw.append("Good triumphs over Evil");
			else if (goodSum == evilSum) bw.append("No victor on this battle field");
			else bw.append("Evil eradicates all trace of Good");
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
