import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
	static int [] parents;
	static int groupCnt;
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = read();
		for (int test = 0; test < T; test++) {
			int N = read(), M = read();
			boolean isTree = true;
			make(N);
			for (int i = 0; i < M; i++) {
				int a = read(), b = read();
				if (isTree && !union(a, b)) {isTree = false;}
			}
			if (isTree && groupCnt == 1) {bw.append("tree\n");}
			else {bw.append("graph\n");}
		}
		bw.flush();
	}

	static void make(int N) {
		parents = new int[N+1];
		groupCnt = N;
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	public static int find(int node) {
		if (parents[node] == node) return node;
		return parents[node] = find(parents[node]);
	}

	public static boolean union(int a, int b) {
		int rootA = find(a), rootB = find(b);
		if (rootA == rootB) return false;
		parents[rootA] = rootB;
		--groupCnt;
		return true;
	}

	static int read () throws Exception {
		int n = System.in.read() & 15, cur;
		while ((cur = System.in.read()) > 32){
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
