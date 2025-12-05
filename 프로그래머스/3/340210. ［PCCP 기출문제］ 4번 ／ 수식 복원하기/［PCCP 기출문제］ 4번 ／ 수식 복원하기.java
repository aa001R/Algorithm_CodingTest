import java.util.*;

class Solution {

    public String[] solution(String[] expressions) {
        boolean[] bases = new boolean[10]; // base 2~9
        Arrays.fill(bases, true);
        
        // 1. C가 숫자인 식으로 가능한 진법(base) 필터링
        for (String exp : expressions) {
            String[] s = exp.split(" ");
            String A = s[0], op = s[1], B = s[2], C = s[4];
            for (int base = 2; base <= 9; base++) {
                if(!bases[base]) continue;
                if (!checkBase(A, base) || !checkBase(B, base)) {
                    bases[base] = false;
                    continue;
                }
                if (C.equals("X")) continue;
                if (!checkBase(C, base)){ bases[base] = false; continue; }

                // base 진수로 변환 후 계산
                int a = toDec(A, base);
                int b = toDec(B, base);
                int c = toDec(C, base);
                if (op.equals("+") && a + b != c) bases[base] = false;
                else if (op.equals("-") && a - b != c) bases[base] = false;
            }
        }
        
        List<String> answers = new ArrayList<>();
        // 2. C = X 인 식들을 해결
        for (String exp : expressions) {
            String[] s = exp.split(" ");
            String A = s[0], op = s[1], B = s[2], C = s[4];

            if (!C.equals("X")) continue;

            String fixed = null;
            boolean diff = false;
            int base = 10;
            for (int i = 2; i <= 9; i++) {
                if (!bases[i]) continue;
                if (!checkBase(A, i) || !checkBase(B, i)) {
                    bases[i] = false;
                    continue;
                }

                int a = toDec(A, i);
                int b = toDec(B, i);
                int v = op.equals("+") ? (a + b) : (a - b);
                if (v < 0) continue;

                String baseVal = toBase(v, i);
                if (!checkBase(baseVal, i)) continue;
                
                // 이전 결과와 비교
                if (fixed == null) {fixed = baseVal; base = i;}
                else if (!fixed.equals(baseVal)) diff = true;   
                if (diff) break;
            }
            
            if (fixed == null || diff) {
                answers.add(A + " " + op + " " + B + " = ?");
            } else {
                answers.add(A + " " + op + " " + B + " = " + fixed);
            }
        }
        return answers.toArray(new String[0]);
    }

    // base에서 각 자리 숫자가 표현 가능한지 체크
    private boolean checkBase(String num, int base) {
        for (char c : num.toCharArray()) {
            int d = c - '0';
            if (d < 0 || d >= base) return false;
        }
        return true;
    }

    // 진법 → 10진수
    private int toDec(String num, int base) {
        int v = 0;
        for (char c : num.toCharArray()) v = v * base + (c - '0');
        return v;
    }

    // 10진수 → 진법 문자열
    private String toBase(int v, int base) {
        if (v == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (v > 0) {
            sb.append(v % base);
            v /= base;
        }
        return sb.reverse().toString();
    }
}
