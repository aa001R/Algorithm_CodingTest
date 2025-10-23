import java.util.*;

class Solution {
    /*
    입차된 후 출차 내역 없다면 23:59 출차된 것으로 간주
    초과 시간이 단위시간으로 나누어 떨어지지 않으면 올림
    "시각 차량번호 내역"
    마지마 시각 입차된 경우 없음
    비용 = 기본 비용 + ceiling(max(출차시간 - 입차시간 - 기본시간, 0) / 단위시간) * 단위 요금 
    */
    public int[] solution(int[] fees, String[] records) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> recordMap = new TreeMap<>(); // 차번호 : 누적 시간
        int limitT = getTime("23:59");
        
        for(String record : records) {
            String temp[] = record.split(" ");
            int time = temp[2].equals("IN") ? -1 : 1;
            time *= getTime(temp[0]);
            recordMap.put(temp[1], recordMap.getOrDefault(temp[1], 0) + time); // -입차시간 +출차시간
        }
        
        int idx = 0, ans[] = new int[recordMap.size()];
        for(int time : recordMap.values()) {
            if(time < 1) time += limitT;
            time -= fees[0];
            int cost = fees[1];
            if(time > 0) cost += (time%fees[2] == 0 ? time/fees[2] : time/fees[2]+1) * fees[3];
            ans[idx++] = cost;
        }
        return ans;
    }
    
    private int getTime(String recordT){
        String[] time = recordT.split(":");
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }
}