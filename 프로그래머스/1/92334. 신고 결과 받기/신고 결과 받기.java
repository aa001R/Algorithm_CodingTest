import java.util.*;

class Solution {
    /* 
    한번에 1번 신고
    횟수 제한 없음 - 서로 다른 유저 계속 신고 가능 단 동일 유저 신고 횟수는 1회
    K 이상 신고 시 이용 정지 - 해당 유저 신고한 모든 유저에게 알림
    모든 신고 내용을 마지막 한번에 처리
    */
    public int[] solution(String[] id_list, String[] report, int k) {
        int userCount = id_list.length;
        int[] answer = new int[userCount];
        
        // ID → 배열 index 매핑
        Map<String, Integer> idToIndex = new HashMap<>();
        for (int i = 0; i < userCount; i++) {
            idToIndex.put(id_list[i], i);
        }
        
        // 신고당한 사람 → 신고한 사람 집합
        Map<String, Set<String>> reportedBy = new HashMap<>();
        for (String id : id_list) {
            reportedBy.put(id, new HashSet<>());
        }
        
        // 중복 신고 제거 후 신고 정보 집계
        for (String r : new HashSet<>(Arrays.asList(report))) {
            String[] parts = r.split(" ");
            String reporter = parts[0];
            String reported = parts[1];
            reportedBy.get(reported).add(reporter);
        }

        // 신고 횟수 기준 k 이상인 사람 판별 후 신고자에게 메일 수 증가
        for (String reported : id_list) {
            Set<String> reporters = reportedBy.get(reported);
            if (reporters.size() < k) continue;
            for (String reporter : reporters) {
                answer[idToIndex.get(reporter)]++;
            }
        }  
        return answer;
    }
}