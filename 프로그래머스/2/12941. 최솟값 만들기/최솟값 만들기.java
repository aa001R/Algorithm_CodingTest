import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        int n = A.length;
        int sum1 = 0, sum2 = 0;
        Arrays.sort(A); Arrays.sort(B);
        for (int i = 0; i < n; i++) {
            sum1 += A[i] * B[n - i - 1];
            sum2 += A[n - i - 1] * B[i];
        }
        return sum1 <= sum2 ? sum1 : sum2;
    }
}

