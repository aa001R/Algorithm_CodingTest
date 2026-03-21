import java.io.*;
import java.util.*;

public class Main {
	static String [] yut = {"D", "C", "B", "A", "E"};
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < 3; i++){
			int count = 0;
			for (int j = 0; j < 4; j++) count += read();
			bw.append(yut[count]).append("\n");
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
