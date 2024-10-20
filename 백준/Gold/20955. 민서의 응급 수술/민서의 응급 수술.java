import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static int [] parents;
	public static void main(String[] args) throws IOException {
		int N = read();
		int M = read();
		init(N);
		int cut = 0;
		while (M-->0) {
			int u = read();
			int v = read();
			if (!union(u, v)) cut++;
		}
		int groupCnt = 0;
		for (int i = 1; i <= N; i++) {
			if (parents[i]<0) groupCnt++;
		}
		System.out.println(cut + groupCnt - 1);
	}

	private static void init(int N) {
		parents = new int[N+1];
		Arrays.fill(parents, -1);
	}

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA == rootB) return false;
		int parent = parents[rootA] < parents[rootB] ? rootA : rootB; // 그룹이 큰 쪽
		int child = parents[rootA] < parents[rootB] ? rootB : rootA; // 그룹이 작은 쪽
		parents[parent] += parents[child];
		parents[child] = parent;
		return true;
	}

	private static int find(int a) {
		if (parents[a] < 0) return a;
		return parents[a] = find(parents[a]);
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if (isNegative) {
			n = System.in.read() & 15;
		}
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? ~n + 1 : n;
	}
}
