class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] result = new String[n];

        for (int i = 0; i < n; i++) {
            // 비트 OR 연산으로 두 지도를 합침
            int merged = arr1[i] | arr2[i];

            // 이진수 문자열로 변환 후 왼쪽을 0으로 채워 길이 맞춤
            String binary = String.format("%" + n + "s", Integer.toBinaryString(merged));

            // '1'은 '#', '0'은 ' ' 로 변환
            binary = binary.replace('1', '#').replace('0', ' ');

            result[i] = binary;
        }

        return result;
    }
}