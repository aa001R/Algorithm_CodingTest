class Solution {
    /* 상하좌우 1칸
    147 왼손, 369 오른손, 2580 더 가까운 엄지 = 같으면 hand
    */
    public String solution(int[] numbers, String hand) {
        // 각 key = 4*3, 0-base로 매칭
        /*  012
            345
            678
            91011
        */
        int left = 9, right = 11;
        StringBuilder sb = new StringBuilder();
        for(int num : numbers){
            switch(num){
                case 1:case 4:case 7:
                    left = num - 1;
                    sb.append("L");
                    break;
                case 3:case 6:case 9:
                    right = num - 1;
                    sb.append("R");
                    break;
                default:
                    num = (num-1) > 0 ? num-1 : 10;
                    int lDis = Math.abs((left/3) - (num/3)) + Math.abs((left%3) - (num%3));
                    int rDis = Math.abs((right/3) - (num/3)) + Math.abs((right%3) - (num%3));
                    if(lDis < rDis || (lDis == rDis && hand.equals("left"))){
                        left = num;
                        sb.append("L");
                    }else {
                        right = num;
                        sb.append("R");                        
                    }
                    break;
            }
        }
        return sb.toString();
    }
}