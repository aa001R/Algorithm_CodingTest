import java.util.*;

class Solution {
    /*
    입차된 후 출차 내역 없다면 23:59 출차된 것으로 간주
    초과 시간이 단위시간으로 나누어 떨어지지 않으면 올림
    시각 차량번호 내역
    마지마 시각 입차된 경우 없음
    */
    public int[] solution(int[] fees, String[] records) {
        List<Integer> answer = new ArrayList<>();
        Map<String, int[]> recordMap = new TreeMap<>(); // 차번호 : {입차시간, 출차시간, 누적 시간}
        int lastTime = getTime("23:59");
        for(String _record : records){
            String [] record = _record.split(" ");
            int time = getTime(record[0]);
            if(record[2].equals("IN")){
                int period = 0;
                if(recordMap.containsKey(record[1])) period = recordMap.get(record[1])[2];
                recordMap.put(record[1], new int[]{time, -1, period});
            }else{
                int [] carInfo = recordMap.get(record[1]);
                carInfo[1] = time;
                carInfo[2] += carInfo[1] - carInfo[0];
            }
        }
        for(String carN : recordMap.keySet()){
            int[] carInfo = recordMap.get(carN);
            if(carInfo[1] < 0){
                carInfo[2] += lastTime - carInfo[0];
            }
            int price = fees[1] + (int)Math.ceil(Math.max(carInfo[2] - fees[0], 0) * 1.0 / fees[2]) * fees[3];
            answer.add(price);
        }
        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }
    
    private int getTime(String time){
        return Arrays.stream(time.split(":"))
                    .mapToInt(Integer::parseInt)
                    .reduce((h, m)-> h*60 + m)
                    .getAsInt();
    }
}