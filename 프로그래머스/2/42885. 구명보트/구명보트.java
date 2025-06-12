import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int left = 0, right = people.length - 1;
        while (left < right) {
            int total = people[left] + people[right];
            if (total <= limit) {
                people[left++] = people[right--] = 0;
                answer++;
            } else {
                right--;
            }
        }
        for (int i = left; i < people.length; i++) {
            if (people[i] != 0) answer++;
        }
        return answer;
    }
}