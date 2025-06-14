import java.util.*;
class Solution {
    public String solution(String number, int k) {
    int len = number.length();
    char[] result = new char[len - k]; // 결과 길이
    int idx = 0;

    for (int i = 0; i < len; i++) {
        char ch = number.charAt(i);

        // 뒤에 있는 값보다 큰 수가 들어오면 앞자리 제거
        while (idx > 0 && result[idx - 1] < ch && k > 0) {
            idx--;
            k--;
        }
        if (idx < result.length) result[idx++] = ch;
    }

    return new String(result);
    }
}