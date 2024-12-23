import java.io.*;
import java.util.*;

public class Main {
	static int N, C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read();
		C = read();
		HashMap<Integer, Integer> map = new HashMap<>();
		ArrayList<Integer> list = new ArrayList<>();
		List<Integer> original = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(read());
			original.add(list.get(i));
			map.put(list.get(i), map.getOrDefault(list.get(i), 0) + 1);
		}

		Collections.sort(list, (o1, o2) -> {
			if (map.get(o1) == map.get(o2)) {
				return original.indexOf(o1) - original.indexOf(o2);
			} else {
				return Integer.compare(map.get(o2), map.get(o1));
			}
		});
		for (Integer num : list) {
			bw.append(Integer.toString(num)).append(" ");
		}
		bw.flush();
	}

	static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) {
			n = System.in.read() & 15;
		}
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? -n : n;
	}
}
