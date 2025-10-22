import java.util.*;

class Solution {
    /*
    이번 달까지 선물 더 준 사람이 다음달 선물 +1
    같거나 기록 없으면, 선물 지수가 더 큰 사람이 더 적은 사람 +1
    선물 지수 = 준 선물 - 받은 선물
    선물 지수가 같으면 다음달에 선물 없음
    */
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        Map<String, Integer> idToIdx = new HashMap<>();
        for (int i = 0; i < n; i++) idToIdx.put(friends[i], i);
        
        int[][] giftState = new int[n][n];
        int[] giftIndex = new int[n];   // 선물 지수 (준 - 받은)
        int[] nextGiftCnt = new int[n]; // 다음 달 받을 선물 수
        
        for (String g : gifts) {
            String[] arr = g.split(" ");
            int giver = idToIdx.get(arr[0]);
            int receiver = idToIdx.get(arr[1]);
            giftState[giver][receiver]++;
            giftIndex[giver]++;
            giftIndex[receiver]--;
        }

        
        // 다음 달 선물 계산
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (giftState[i][j] > giftState[j][i] ||
                    (giftState[i][j] == giftState[j][i] && giftIndex[i] > giftIndex[j])) {
                    nextGiftCnt[i]++;
                } else if (giftState[i][j] < giftState[j][i] ||
                           (giftState[i][j] == giftState[j][i] && giftIndex[i] < giftIndex[j])) {
                    nextGiftCnt[j]++;
                }
                // 같으면 아무 일 없음
            }
        }
            
        return Arrays.stream(nextGiftCnt).max().getAsInt();
    }
}