import java.util.*;

class Solution {
    static Integer[][] dpMax, dpMin;
    static String [] gArr;
    public int solution(String arr[]) {
        int n = arr.length;
        gArr = arr;
        dpMax = new Integer[n][n];
        dpMin = new Integer[n][n];
        return getMax(0, n - 1);
    }
    
    private int getMax(int start, int end) {
        // 분할 범위 Key 조회
        // start == end 일 경우 해당 인덱스 숫자
        if (dpMax[start][end] != null) return dpMax[start][end];
        if (start == end) return dpMax[start][end] = Integer.parseInt(gArr[start]);

        // start ~ end idx 범위에서 최대 연산 결과 계산
        int max = Integer.MIN_VALUE;
        for (int op = start + 1; op < end; op += 2) {
            int leftMax = getMax(start, op - 1);
            int rightMax = getMax(op + 1, end);
            int rightMin = getMin(op + 1, end);

            int result = gArr[op].equals("+")
                ? leftMax + rightMax
                : leftMax - rightMin;

            max = Math.max(max, result);
        }
        return dpMax[start][end] = max;
    }
    
    private int getMin(int start, int end) {
        // 분할 범위 Key 조회
        // start == end 일 경우 해당 인덱스 숫자
        if (dpMin[start][end] != null) return dpMin[start][end];
        if (start == end) return dpMin[start][end] = Integer.parseInt(gArr[start]);

        // start ~ end idx 범위에서 최소 연산 결과 계산
        int min = Integer.MAX_VALUE;
        for (int op = start + 1; op < end; op += 2) {
            int leftMin = getMin(start, op - 1);
            int rightMin = getMin(op + 1, end);
            int rightMax = getMax(op + 1, end);

            int result = gArr[op].equals("+")
                ? leftMin + rightMin
                : leftMin - rightMax;

            min = Math.min(min, result);
        }
        return dpMin[start][end] = min;
    }
}