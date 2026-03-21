import java.io.*;
import java.util.*;

public class Main {
	static int[][] finger = {
		{1, 1},  // 1%8, 9%8
		{2, 0},  // 2%8, 8%8
		{3, 7},  // 3%8, 7%8
		{4, 6},  // 4%8, 6%8
		{5, 5},  // 5%8, 13%8
	};
	public static void main(String[] args) throws IOException {
		int n = read();
		int nMod = n % 8;
		for (int i = 0; i < finger.length; i++){
			for (int j = 0; j < finger[i].length; j++){
				if (nMod != finger[i][j]) continue;
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
