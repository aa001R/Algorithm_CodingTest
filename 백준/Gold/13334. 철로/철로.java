import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int n = read();
		List<int []> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int home = read(), office = read();
			list.add(new int[]{i, home});
			list.add(new int[]{i, office});
		}
		int d = read();

		list.sort(Comparator.comparing(a -> a[1]));
		int [] person = new int [n];
		int max = 0, sum = 0, start = 0;
		for(int i = 0; i < list.size(); i++){
			if(start < i && list.get(i)[1] - list.get(start)[1] > d) {
				max = Math.max(max, sum);
				while(start < i && list.get(i)[1] - list.get(start)[1] > d) {
					if (person[list.get(start++)[0]]-- == 2) sum--;
				}
			}
			if(++person[list.get(i)[0]] == 2) sum++;
		}
		max = Math.max(max, sum);
		System.out.println(max);
	}

	// 빠른 입력을 위한 read 함수
	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if (isNegative) n = System.in.read() & 15;
		while ((cur = System.in.read()) > 32) n = (n << 3) + (n << 1) + (cur & 15);
		return isNegative ? -n : n;
	}
}
