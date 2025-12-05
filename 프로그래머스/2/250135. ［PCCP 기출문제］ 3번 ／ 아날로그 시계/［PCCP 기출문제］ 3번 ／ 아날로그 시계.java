import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    // 작은 허용 오차 (시간 비교용)
    private static final double EPS = 1e-9;
    // 중복 제거 시 사용하는 시간 근접 판정
    private static final double DUP_EPS = 1e-7;

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        // 구간의 초 단위 시작과 끝 (포함)
        int start = h1 * 3600 + m1 * 60 + s1;
        int end   = h2 * 3600 + m2 * 60 + s2;

        // 초-분 주기 T_sm = 3600 / 59  (초와 분이 겹치는 간격)
        double T_sm = 3600.0 / 59.0;
        // 초-시 주기 T_sh = 43200 / 719 (초와 시가 겹치는 간격)
        double T_sh = 43200.0 / 719.0;

        List<Double> events = new ArrayList<>(); // 모든 이벤트 시간(초 단위 실수) 저장

        // --- 초-분 시퀀스에서 start..end에 들어오는 항 수학적으로 생성 ---
        // k 범위 계산 (포함 여부를 위해 eps 이용)
        int kmin_sm = (int) Math.ceil((start - EPS) / T_sm);
        if (kmin_sm < 0) kmin_sm = 0; // 안전치
        int kmax_sm = (int) Math.floor((end + EPS) / T_sm);

        for (int k = kmin_sm; k <= kmax_sm; k++) {
            double t = k * T_sm;
            // t가 구간 내에 존재하면 추가
            if (t + EPS >= start && t <= end + EPS) events.add(t);
        }

        // --- 초-시 시퀀스에서 start..end에 들어오는 항 생성 ---
        int kmin_sh = (int) Math.ceil((start - EPS) / T_sh);
        if (kmin_sh < 0) kmin_sh = 0;
        int kmax_sh = (int) Math.floor((end + EPS) / T_sh);

        for (int k = kmin_sh; k <= kmax_sh; k++) {
            double t = k * T_sh;
            if (t + EPS >= start && t <= end + EPS) events.add(t);
        }

        // 정렬 후 근접값(중복) 제거
        Collections.sort(events);
        int count = 0;
        double last = Double.NEGATIVE_INFINITY;
        for (double t : events) {
            if (Double.isInfinite(last) || Math.abs(t - last) > DUP_EPS) {
                // 이전에 센 이벤트와 충분히 다르면 새 이벤트로 카운트
                count++;
                last = t;
            } else {
                // 이전 이벤트와 거의 같은 시간(중복)이면 건너뜀
            }
        }

        return count;
    }
}
