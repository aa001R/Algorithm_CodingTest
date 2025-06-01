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
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 1; i < parents.length; i++) {
            int root = find(parents[i]);
            countMap.put(root, countMap.getOrDefault(root, 0) + 1);
        }
        Iterator<Integer> it = countMap.values().iterator();
        int count1 = it.next(),count2 = it.next();
        
        return Math.abs(count1 - count2);
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