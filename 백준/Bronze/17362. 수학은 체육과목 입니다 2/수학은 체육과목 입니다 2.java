import java.io.*;
import java.util.*;

public class Main {
	static int [][] finger = {
		{1, 9},
		{2, 8},
		{3, 7},
		{4, 6},
		{5, 13},
	};
	public static void main(String[] args) throws IOException {
		int n = read();
		for (int i = 0; i < finger.length; i++){
			for (int j = 0; j < finger[i].length; j++){
				if ((n - finger[i][j]) % 8 != 0) continue;
				System.out.println(i+1);
				return;
			}

		}
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if(isNegative) n = System.in.read() & 15;
		while((cur = System.in.read()) > 32) n = (n << 3) + (n << 1) + (cur & 15);
		return isNegative? -n : n;
	}
}
