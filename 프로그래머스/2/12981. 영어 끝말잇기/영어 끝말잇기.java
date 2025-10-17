import java.util.*;
/*
가장 먼저 탈락하는 사람의 번호 & 그 사람이 자신의 몇 번째 차례 탈락하는지
사람의 수 n과 사람들이 순서대로 말한 단어 words
*/
class Solution {
    public int[] solution(int n, String[] words) {
        int failN = 0, failOrder = 0;
        Set<String> wordSet = new HashSet<>();
        char preChar = words[0].charAt(0);
        for(int i = 0; i < words.length; i++){
            if(words[i].length() <= 1
                || preChar != words[i].charAt(0)
                || wordSet.contains(words[i])) {
                failN = i % n + 1;
                failOrder = i / n + 1;
                break;
            }
            preChar = words[i].charAt(words[i].length()-1);
            wordSet.add(words[i]);
        }
        return new int[] {failN, failOrder};
    }
}