import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer>[] tree;
	static int [][] dp;
	public static void main(String[] args) throws IOException {
		N = read();
		tree = new ArrayList[N+1];
		dp = new int[N+1][2];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		for (int i = 0; i < N-1; i++) {
			int from = read(), to = read();
			tree[from].add(to);
			tree[to].add(from);
		}
		dfs(1, -1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	// 올바른 백트래킹 구조
	static void dfs(int node, int parent) {
		// 초기값 설정
		dp[node][0] = 0;  // 현재 노드가 얼리 아답터가 아닐 때
		dp[node][1] = 1;  // 현재 노드가 얼리 아답터일 때 (자기 자신 포함)
		for (int child : tree[node]) { // 모든 자식들에 대해 계산
			if (child == parent) continue;  // 부모로 다시 가지 않도록
			dfs(child, node);  // 자식 먼저 계산
			dp[node][1] += Math.min(dp[child][0], dp[child][1]);  // 자식은 자유롭게 선택
			dp[node][0] += dp[child][1];                          // 자식은 반드시 얼리 아답터
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
