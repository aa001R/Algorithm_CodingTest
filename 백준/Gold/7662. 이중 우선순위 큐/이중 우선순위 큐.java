import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			TreeMap<Integer, Integer> tree = new TreeMap<>();
			int k = Integer.parseInt(br.readLine());

			for (int i = 0; i < k; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				char cmd = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());

				if (cmd == 'I')
					tree.put(num, tree.getOrDefault(num, 0) + 1);
				else if (cmd == 'D') {
					if (tree.isEmpty()) continue;

					int res = 0;
					if (num == 1)
						res = tree.lastKey();
					else if (num == -1) {
						res = tree.firstKey();
					}
					
					if (tree.get(res) == 1)
						tree.remove(res);
					else
						tree.put(res, tree.get(res) - 1);
				}
			}
			if (tree.isEmpty())
				sb.append("EMPTY\n");
			else {
				sb.append(tree.lastKey()).append(" ").append(tree.firstKey()).append("\n");
			}
		}
		System.out.print(sb);
		br.close();
	}
}