import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        ArrayList<String> dic = new ArrayList<String>();
        for(int i = 0 ; i < 26; i++) {
            dic.add(String.valueOf((char)('A'+i)));
        }

        for(int i = 0 ; i < msg.length() ; i++) {
            for(int j = dic.size()-1 ; j >= 0 ; j--) {
                if(msg.substring(i).startsWith(dic.get(j))) { // w = dic.get(j)
                    i += dic.get(j).length() - 1;   // for문에서 i++ 하므로 -1
                    answer.add(j+1);
                    if(i+1 < msg.length()) dic.add(dic.get(j)+msg.charAt(i+1));
                    break;
                }
            }
        }
        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }
}