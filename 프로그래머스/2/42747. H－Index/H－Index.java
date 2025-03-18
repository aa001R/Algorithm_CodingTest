import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        return answer = binarySearch(citations, 0, citations[citations.length - 1]);
    }
    
    int binarySearch(int [] arr, int left, int right){
        int h = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            if (check(mid, arr)) {
                if (h < mid) h = mid;
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return h;
    }
    
    boolean check(int mid, int [] arr){
        int above = 0, below = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= mid) below++;
            if (arr[i] >= mid) above++; 
        }
        if (mid <= above && mid >= below) return true;
        return false;
    }
    
}