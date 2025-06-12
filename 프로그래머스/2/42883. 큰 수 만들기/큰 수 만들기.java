class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        boolean [] remove = new boolean[number.length()];
        for (int i = 0; i < number.length() && k > 0; i++) {
            if (remove[i]) continue;
            for (int j = i + 1; j < number.length() && j <= i + k; j++) {
                if (remove[j]) continue;
                if (number.charAt(i) < number.charAt(j)) {
                    remove[i] = true;
                    k--;
                    break;
                }
            }
        }
        for (int i = number.length() - 1; i >= 0 && k > 0; i--) {
            if (remove[i]) continue;
            remove[i] = true;
            k--;
        }
        
        for (int i = 0; i < number.length(); i++) {
            if (remove[i]) continue;
            answer.append(number.charAt(i));
        }
        return answer.toString();
    }
}