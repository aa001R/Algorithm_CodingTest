import java.util.*;
import java.util.regex.*;

class Solution {
    /*
    HEAD NUMBER TAIL
    숫자전문자 숫자{1, 5} 나머지 
    
    1. HEAD 기준 사전 순 대소문자 구문 x
    2. NUMBER 숫자순 숫자 앞의 0은 무시
    3. 입력 순서
    */
    class FileName implements Comparable<FileName>{
        String head, number, tail;
        int num;
        FileName(String head, String number, String tail){
            this.head = head;
            this.number = number;
            num = Integer.parseInt(number);
            this.tail = tail;
        }
        @Override
        public int compareTo(FileName other){
            int headCompare = this.head.compareToIgnoreCase(other.head);
            if (headCompare == 0) {
                return Integer.compare(this.num, other.num);
            }
            return headCompare;
        }
        @Override
        public String toString(){
            return head+number+tail;            
        }
    }
    public String[] solution(String[] files) {
        List<FileName> answer = new ArrayList<>();
        Pattern p = Pattern.compile("([A-Za-z .-]+)(\\d{1,5})(.*)");
        for(String file : files){
            Matcher m = p.matcher(file);
            m.find();
            answer.add(new FileName(m.group(1), m.group(2), m.group(3)));
        }
        Collections.sort(answer);
        return answer.stream().map(a -> a.toString()).toArray(String[]::new);
    }
}