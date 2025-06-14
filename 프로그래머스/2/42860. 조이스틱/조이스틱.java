class Solution {
    public int solution(String name) {
        int n = name.length();
        int answer = 0;
        
        // 1. 각 문자를 바꾸는데 필요한 최소 조작 횟수 계산
        for (int i = 0; i < n; i++) {
            char c = name.charAt(i);
            // 위로 이동 (A→B→C→...)
            int up = c - 'A';
            // 아래로 이동 (A→Z→Y→...)  
            int down = 'Z' - c + 1;
            answer += Math.min(up, down);
        }
        
        // 2. 좌우 이동의 최소값 구하기
        int minMove = n - 1; // 기본: 맨 끝까지 오른쪽으로 이동
        for (int i = 0; i < n; i++) {
            // 현재 위치에서 연속된 A의 끝 찾기
            int next = i + 1;
            while (next < n && name.charAt(next) == 'A') {
                next++;
            }
            
            // 전략 1: 오른쪽 i 먼저 처리 후 왼쪽 처리
            // 0 → i (오른쪽 이동) → 0 (되돌아옴(왼쪽이동)) → (n-1) → ... → next (왼쪽 이동)
            int case1 = i * 2 + (n - next);
            // 전략 2: 왼쪽 next 먼저 처리 후 오른쪽 처리  
            // 0 → (n-1) → ... → next (왼쪽 이동) → 0 (되돌아옴(오른쪽이동)) → i (오른쪽 이동)
            int case2 = (n - next) * 2 + i;
            
            minMove = Math.min(minMove, Math.min(case1, case2));
        }
        
        return answer + minMove;
    }
}