import java.util.*;

class Solution {
    /* 
    한번에 1번 신고
    횟수 제한 없음 - 서로 다른 유저 계속 신고 가능 단 동일 유저 신고 횟수는 1회
    K 이상 신고 시 이용 정지 - 해당 유저 신고한 모든 유저에게 알림
    모든 신고 내용을 마지막 한번에 처리
    */
    public int[] solution(String[] id_list, String[] report, int k) {
        int n = id_list.length;
        int[] answer = new int[n];
        // id별 신고당한 횟수
        int[] reportCnt = new int[n];
        // 유저idx : id
        Map<String, Integer> userIdx = new HashMap<>();
        // 신고당한사람 : 신고한사람[]
        Map<String, Set<String>> reportees = new HashMap<>();
        // user - idx 초기화
        for(int i = 0; i < n; i++){
            userIdx.put(id_list[i], i);
            reportees.put(id_list[i], new HashSet<>());
        }
        // 신고 당한 사람 key로 신고 횟수 세기
        for(String re: report){
            String[] r = re.split(" ");
            if(reportees.get(r[1]).contains(r[0])) continue;
            reportCnt[userIdx.get(r[1])]++;
            reportees.get(r[1]).add(r[0]);
        }
        // 신고 횟수 조회하며 정지된 사람 판별 & 신고한 사람 알림 처리
        for(int i = 0; i < n; i++){
            if(reportCnt[i] < k) continue;
            for(String reporter : reportees.get(id_list[i])){
                answer[userIdx.get(reporter)]++;
            }
        }        
        return answer;
    }
}