import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Set<Integer> lostSet = new HashSet<>();
        Set<Integer> reserveSet = new HashSet<>();

        // 일단 다 추가
        for (int l : lost) lostSet.add(l);
        for (int r : reserve) reserveSet.add(r);

        // 여벌이 있는데 도난당한 경우 제거
        Set<Integer> overlap = new HashSet<>(lostSet);
        overlap.retainAll(reserveSet); // 교집합
        lostSet.removeAll(overlap);
        reserveSet.removeAll(overlap);

        // 앞뒤 학생 확인해서 빌려줄 수 있으면 처리
        for (int l : new HashSet<>(lostSet)) {
            if (reserveSet.contains(l - 1)) {
                reserveSet.remove(l - 1);
                lostSet.remove(l);
            } else if (reserveSet.contains(l + 1)) {
                reserveSet.remove(l + 1);
                lostSet.remove(l);
            }
        }

        return n - lostSet.size();  // 체육복 못 받은 학생 수만큼 차감
    }
}
