class Solution {
    /*
    징검다리 건너기
    디딤돌 숫자 적힘 & 한번 밟을때마다 -1 & 0이되면 못 밟음 & 그 다음 디딤돌로 건너뜀 가능
    가장 가까운 디딤돌로 이동
    최대 거널 수 있는 수 return
    */
    public int solution(int[] stones, int k) {
        int answer = 0, left = 0, right = 0;
        for(int s : stones) right = Math.max(s+1, right);
        while(left < right){ // upper bound - 1
            int mid = (left + right) / 2;
            if(canCross(stones, k, mid)) left = mid + 1;
            else right = mid;
        }
        return right - 1;
    }
    
    private boolean canCross(int[] stones, int k, int n){
        int count = 0;
        for(int s : stones){
            if(s - n < 0){
                if(++count >= k) return false;
            }else{
                count = 0;
            }
        }
        return true;
    }
}