import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<Integer>[] tree;
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = read();
		tree = new ArrayList[N + 1];
		for (int i = 0; i < N; i++)
			tree[i] = new ArrayList<>();
		int root = -1;
		for (int node = 0; node < N; node++) {
			int p = read();
			if (p == -1) {
				root = node;
			} else {
				tree[p].add(node);
			}
		}
		int delete = read();
		if (delete == root) {
			bw.append(Integer.toString(0));
		} else {
			bw.append(Integer.toString(dfs(root, delete, new boolean[N + 1], 0)));
		}
		bw.flush();
	}

	static int dfs(int node, int delete, boolean[] visited, int cnt) {
		visited[node] = true;
		int children = 0;
		for (int cur : tree[node]) {
			// 연결 노드 탐색
			if (cur != delete && !visited[cur]) {
				children++;
				cnt += dfs(cur, delete, visited, 0);
			}
		}
		if (children == 0) {
			return ++cnt;
		}
		return cnt;
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
