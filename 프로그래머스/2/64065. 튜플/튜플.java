import java.util.*;

class Solution {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2);
        String[] setStrs = s.split("\\},\\{");
        
        List<List<Integer>> sets = new ArrayList<>();
        for (String str : setStrs) {
            String[] nums = str.split(",");
            List<Integer> temp = new ArrayList<>();
            for (String num : nums) temp.add(Integer.parseInt(num));
            sets.add(temp);
        }
        sets.sort(Comparator.comparingInt(List::size));
        List<Integer> answerList = new ArrayList<>();
        for (List<Integer> set : sets) {
            for (int num : set) {
                if (!answerList.contains(num)) answerList.add(num);
            }
        }
        
        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }
}