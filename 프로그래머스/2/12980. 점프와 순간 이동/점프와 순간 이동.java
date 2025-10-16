import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;
        while(n > 0){
            ans += n % 2 == 1 ? 1 : 0;
            n /= 2;
        }
        return ans;
    }
}