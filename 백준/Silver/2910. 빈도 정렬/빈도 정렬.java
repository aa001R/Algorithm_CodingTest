import java.io.*;
import java.util.*;

public class Main {
	static int N, C;
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read();
		C = read();
		HashMap<Integer, Integer> map = new LinkedHashMap<>();

		for (int i = 0; i < N; i++) {
			int key = read();
			map.put(key, map.getOrDefault(key, 0) + 1);
		}

		List<Integer> keySet = new ArrayList<>(map.keySet());
		Collections.sort(keySet, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return map.get(o2).compareTo(map.get(o1));
			}
		});

		for (Integer key : keySet) {
			for(int i=0;i<map.get(key);i++){
				bw.append(Integer.toString(key)).append(" ");
			}
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
