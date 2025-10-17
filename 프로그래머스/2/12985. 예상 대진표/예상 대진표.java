class Solution
{
    /*
    참가자 번호 연속끼리 게임
    처음 라운드에서 A번 참가자와 B번 참가자는 몇번째 라운드에서 만나는지
    */
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        while((a=(a+1)/2) != (b=(b+1)/2)){
            answer++;
        }
        return answer;
    }
}