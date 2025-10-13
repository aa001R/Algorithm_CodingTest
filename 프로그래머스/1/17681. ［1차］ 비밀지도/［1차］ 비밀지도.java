class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] board = new String[n];
        for (int i = 0; i < n; i++){
            String row = Integer.toBinaryString(arr1[i] | arr2[i]);
            board[i] = String.format("%"+n+"s", Integer.toBinaryString(arr1[i] | arr2[i]))
                .replace("0", " ").replace("1", "#");
        }
        return board;
    }
}