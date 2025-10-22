class Solution {
    public String solution(int[] numbers, String hand) {
        int left = 9, right = 11;
        StringBuilder sb = new StringBuilder();
        for(int n : numbers){
            int num = (n == 0) ? 10 : n - 1;
            if (n == 1 || n == 4 || n == 7) { left = num; sb.append('L'); }
            else if (n == 3 || n == 6 || n == 9) { right = num; sb.append('R'); }
            else {
                int l = Math.abs(left / 3 - num / 3) + Math.abs(left % 3 - num % 3);
                int r = Math.abs(right / 3 - num / 3) + Math.abs(right % 3 - num % 3);
                if (l < r || (l == r && hand.equals("left"))) { left = num; sb.append('L'); }
                else { right = num; sb.append('R'); }
            }
        }
        return sb.toString();
    }
}