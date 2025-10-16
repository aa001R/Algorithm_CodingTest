class Solution {
    // 다음 큰 숫자(next higher number with same bit count)
    /*
    1. 오른쪽부터 처음 등장하는 "01" 패턴을 "10"로 변경
    2. 그 이전(오른쪽)의 비트들을 가장 작은 형태로 재배치(=오른쪽으로 모으기)
    */
    public int solution(int n) {
        int step1 = n & -n; // 오른쪽에서 첫 1이 나오는 지점 탐색 = 가장 낮은 1비트 위치
        int step2 = n + step1; // '01' -> '10' 변환 : 동일한 위치 1 덧셈 01 + 01 = 10
        int step3 = n ^ step2; // step2 이전 비트들만 구하기
        int step4 = step3 / step1; // 오른쪽의 1들을 가장 오른쪽으로 모음 (가장 낮은 1비트 위치를 1번째로 쉬프트 ex /2(=..10) → >>1, /4(=..100) → >>2)
        int step5 = step4 >> 2; // '01' and '10' 패턴 포함되어 있으니 두 1 제거
        return step2 | step5;
    }
}