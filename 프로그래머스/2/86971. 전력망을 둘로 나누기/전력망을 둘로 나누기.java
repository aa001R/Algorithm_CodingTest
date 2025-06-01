import java.util.*;
class Solution {
    static int [] parents;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        parents = new int[n+1];
        for (int i = 0; i < wires.length; i++) {
            cuttingWires(n, i, wires);
            answer = Math.min(answer, countDiff(parents));
        }
        return answer;
    }
    
    private void cuttingWires(int n, int cuttingIdx, int[][] wirtes){
        init();
        for (int i = 0; i < wirtes.length; i++) {
            if (i == cuttingIdx) continue;
            union(wirtes[i][0], wirtes[i][1]);
        }
    }
    
    private int countDiff(int [] parents){
        int rootOne = find(1), oneUnionCnt = 1;
        for (int i = 2; i < parents.length; i++) {
            if(rootOne != find(i)) continue;
            oneUnionCnt++;
        }
        int otherUnionCnt = parents.length - 1 - oneUnionCnt;
        return Math.abs(otherUnionCnt - oneUnionCnt);
    }
    
    private void init(){
        for (int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }
    }
    
    private int find(int a){
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
    
    private void union(int a, int b){
        int rootA = find(a), rootB = find(b);
        parents[rootB] = rootA;
    }
}