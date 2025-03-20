import java.util.*;

class Solution {
    public static int [] parents;
    public int solution(int n, int[][] computers) {
        int answer = n; //초기 네트워크 수 = 컴퓨터 수
        parents = new int [n];
        init(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (computers[i][j] == 0) continue;
                if (union(i, j)) {
                    answer--;
                    computers[i][j] = computers[i][j] = 0;
                }
            }
        }
        return answer;
    }
    
    public void init(int n) {
        for(int i = 0; i < n; i++){
            parents[i] = i;
        }
    }
    
    public int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
    
    public boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return false;
        parents[rootA] = rootB;
        return true;
    }
}