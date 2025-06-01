import java.util.*;

class Solution {
    int N, min;                     // 전체 송전탑 수와 최소 차이값
    List<Integer>[] graph;         // 인접 리스트로 구성된 트리
    boolean[] visited;             // 방문 여부 체크 (역방향 방지)

    public int solution(int n, int[][] wires) {
        this.N = n;
        this.min = n;

        // 그래프 초기화
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 양방향 간선 정보 등록
        for (int[] wire : wires) {
            int a = wire[0], b = wire[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new boolean[n + 1];
        dfs(1); // 루트 노드에서 DFS 시작

        return min;
    }

    // DFS: 서브트리 크기를 반환하면서 두 트리로 나눈 경우 차이 갱신
    private int dfs(int node) {
        visited[node] = true;
        int subtreeSize = 1; // 자기 자신 포함

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                subtreeSize += dfs(neighbor); // 자식 서브트리 크기 누적
            }
        }

        // 현재 노드에서 절단한다고 가정했을 때 두 트리 차이 계산
        int diff = Math.abs(N - 2 * subtreeSize);
        min = Math.min(min, diff);

        return subtreeSize; // 현재 노드를 루트로 한 서브트리 크기 반환
    }
}
