import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 1. 장르별 곡 정보 저장 (HashMap<String, ArrayList<Map<곡 번호, 재생 횟수>>>)
        HashMap<String, ArrayList<Map<Integer, Integer>>> musicMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            musicMap.computeIfAbsent(genres[i], k -> new ArrayList<>()).add(Map.of(i, plays[i]));
        }

        // System.out.println("\n📌 [Step 1] 초기 HashMap (musicMap)");
        // musicMap.forEach((k, v) -> System.out.println(k + " -> " + v));

        // 2. 각 장르별 총 재생 횟수를 계산하고 내림차순 정렬
        Map<String, Integer> genrePlayCount = musicMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                              .mapToInt(m -> m.values().iterator().next())
                              .sum()
                ));

        // System.out.println("\n📌 [Step 2] 장르별 총 재생 횟수");
        // genrePlayCount.forEach((k, v) -> System.out.println(k + " -> " + v));

        // 3. 총 재생 횟수 기준으로 장르 정렬 후 곡 리스트 처리
        List<Integer> sortedSongs = musicMap.entrySet().stream()
                .sorted((a, b) -> Integer.compare(genrePlayCount.get(b.getKey()), genrePlayCount.get(a.getKey()))) // 총 재생 횟수 기준 내림차순 정렬
                // .peek(e -> System.out.println("🟢 [Step 3] 정렬된 장르: " + e.getKey() + " (총 재생 횟수: " + genrePlayCount.get(e.getKey()) + ")"))
                .flatMap(entry -> entry.getValue().stream()
                        .sorted((a, b) -> {
                            int comparePlay = Integer.compare(b.values().iterator().next(), a.values().iterator().next());
                            return (comparePlay != 0) ? comparePlay : Integer.compare(a.keySet().iterator().next(), b.keySet().iterator().next());
                        })
                        // .peek(e -> System.out.println("🔵 [Step 4] 장르 '" + entry.getKey() + "' 정렬된 곡 리스트: " + e))
                        .limit(2) // 각 장르에서 최대 2곡 선택
                        .map(m -> m.keySet().iterator().next())) // 곡 번호만 추출
                // .peek(e -> System.out.println("🟡 [Step 5] 선택된 곡: " + e))
                .collect(Collectors.toList());

        // 4. 결과를 int[] 배열로 변환
        int[] answer = sortedSongs.stream().mapToInt(i -> i).toArray();

        return answer;
    }
}