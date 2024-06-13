import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<int[]>[] list;
	static boolean[] visited;
	static int N, MAX = 0, far;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}

		// list 구성
		StringTokenizer st;
		int v1, v2, dist;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken()) - 1;
			while (true) {
				v2 = Integer.parseInt(st.nextToken()) - 1;
				if (v2 == -2) break;
				dist = Integer.parseInt(st.nextToken());
				list[v1].add(new int[] { v2, dist });
			}
		}
		// 임의의 노드(0)에서 부터 가장 먼 노드를 찾는다. 이때 찾은 노드를 node에 저장한다.
		visited = new boolean[N];
		dfs(0, 0);

		// 가장 먼 노드에서 출발해서 가장 멀리 떨어진 거리가 트리의 지름이 된다.
		visited = new boolean[N];
		dfs(far, 0);

		System.out.println(MAX);
	}

	private static void dfs(int n, int dist) {
		if (MAX < dist) {
			MAX = dist;
			far = n;
		}
		visited[n] = true;
		for (int[] node : list[n]) {
			if (visited[node[0]]) continue;
			dfs(node[0], dist + node[1]);
		}
	}
}