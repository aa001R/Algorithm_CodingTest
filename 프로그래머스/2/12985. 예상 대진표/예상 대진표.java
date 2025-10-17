class Solution
{
    /*
    참가자 번호 연속끼리 게임
    처음 라운드에서 A번 참가자와 B번 참가자는 몇번째 라운드에서 만나는지
    // 이진 트리(Binary Tree) 공통 조상 찾기 = 비트에서 공통 조상 찾기
    */
    public int solution(int n, int a, int b)
    {
        // a-1 : 참가자 번호 1-base > 0-base로 변경
        // xor : 가장 높은 차이 비트 위치 = 둘이 갈라지는 시점 탐색
        // 32 - numberOfLeading : 해당 비트 길이
        return 32 - Integer.numberOfLeadingZeros((a-1)^(b-1));        
    }
}