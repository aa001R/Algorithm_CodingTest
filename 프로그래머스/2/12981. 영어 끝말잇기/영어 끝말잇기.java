import java.util.*;
/*
가장 먼저 탈락하는 사람의 번호 & 그 사람이 자신의 몇 번째 차례 탈락하는지
사람의 수 n과 사람들이 순서대로 말한 단어 words
*/
class Solution {
    public int[] solution(int n, String[] words) {
        int failN = 0, failOrder = 0;
        Set<String> wordSet = new HashSet<>();
        for(int i = 0; i < words.length; i++){
            if(words[i].length() <= 1
                || (i > 0 && words[i-1].charAt(words[i-1].length()-1) != words[i].charAt(0))
                || wordSet.contains(words[i])) {
                failN = i % n + 1;
                failOrder = i / n + 1;
                break;
            }
            wordSet.add(words[i]);
        }
        return new int[] {failN, failOrder};
    }
}