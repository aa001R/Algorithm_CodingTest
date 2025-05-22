import java.util.*;

class Solution {
    class Airport implements Comparable<Airport>{
        String name;
        boolean isArrived;
        Airport(String name){
            this.name = name;
            this.isArrived = false;
        }
        @Override
        public int compareTo(Airport other){
            return this.name.compareTo(other.name);   
        }
    }
    
    static HashMap<String, ArrayList<Airport>> airports;
    public String[] solution(String[][] tickets) {
        airports = new HashMap<>();
        for (String [] ticket : tickets){
            airports.computeIfAbsent(ticket[0], k -> new ArrayList<>())
                    .add(new Airport(ticket[1]));
        }
        
        for (String key : airports.keySet()) {
            Collections.sort(airports.get(key));
        }
        
        String [] ans = new String[tickets.length+1];
        ans[0] = "ICN";
        dfs(1, ans);
        return ans;
    }
    
    boolean dfs (int depth, String [] visited){
        if (depth >= visited.length){
           return true; 
        }
        List<Airport> nextList = airports.get(visited[depth - 1]);
        if (nextList == null) return false;

        for (Airport des : nextList) {
            if (des.isArrived) continue;
            des.isArrived = true;
            visited[depth] = des.name;
            if (dfs(depth + 1, visited)) return true;
            des.isArrived = false;
        }
        return false;
    }
    
}