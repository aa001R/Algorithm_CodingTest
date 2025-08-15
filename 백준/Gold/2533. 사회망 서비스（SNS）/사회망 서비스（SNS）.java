import java.io.*;
import java.util.*;

public class Main {
	static int[] head, next, to;
	static int[] dp0, dp1;  // 2차원 배열을 1차원으로 분리
	static boolean[] visited;
	static int edgeCount = 0;
	public static void main(String[] args) throws IOException {
		int n = read();
		// 배열 기반 인접 리스트
		head = new int[n + 1];
		for (int i = 1; i <= n; i++) head[i] = -1;  // Arrays.fill 대신 루프
		next = new int[n << 1];  // 2*n 대신 비트 시프트
		to = new int[n << 1];
		// 간선 정보 입력 (StringTokenizer 없이)
		for (int i = 0; i < n - 1; i++) {
			int u = read(), v = read();
			addEdge(u, v);
			addEdge(v, u);
		}
		// 1차원 DP 배열로 메모리 효율성 향상
		dp0 = new int[n + 1];  // 얼리 어답터가 아닌 경우
		dp1 = new int[n + 1];  // 얼리 어답터인 경우
		visited = new boolean[n + 1];
		dfs(1);
		System.out.println(Math.min(dp0[1], dp1[1]));
	}

	static void addEdge(int u, int v) {
		to[edgeCount] = v;
		next[edgeCount] = head[u];
		head[u] = edgeCount++;
	}

	static void dfs(int node) {
		visited[node] = true;
		dp1[node] = 1;
		for (int i = head[node]; i != -1; i = next[i]) {
			int child = to[i];
			if (!visited[child]) {
				dfs(child);
				dp0[node] += dp1[child];
				dp1[node] += Math.min(dp0[child], dp1[child]);
			}
		}
	}

	// 빠른 입력을 위한 read 함수
	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if (isNegative) n = System.in.read() & 15;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? -n : n;
	}
}
