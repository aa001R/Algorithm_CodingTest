class Solution {
    static int order;
    static final String [] ALPHA = {"A","E","I","O","U"};
    public int solution(String word) {
        findOrderDfs("", word);
        return order;
    }
    
    private boolean findOrderDfs(String str, String word){
        if (str.equals(word)) return true;        
        if (str.length() == 5) return false;
        for (int i = 0; i < 5; i++) {
            order++;
            if (findOrderDfs(str+ALPHA[i], word)) return true;
        }
        return false;
    }
}