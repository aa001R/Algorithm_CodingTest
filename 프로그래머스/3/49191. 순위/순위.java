class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        boolean[][] chk = new boolean[n + 1][n + 1]; // 승패 여부를 저장할 배열[][]

        for (int[] r : results) {
            chk[r[0]][r[1]] = true; // r[0]이 r[1]을 이김
        }

		// 플로이드-워셜 알고리즘
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(i !=j && chk[i][k] && chk[k][j]) {
                        chk[i][j] = true;
                    }
                }
            }
        }

		// i번 선수가 다른 모든 선수와 승/패가 정해져 있으면 카운트
        for(int i = 1; i <= n; i++) {
            boolean definite = true;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (!(chk[i][j] || chk[j][i])) { //chk[i][j] && chk[j][i] 가 false
                    definite = false;
                    break;
                }
            }
            if (definite) answer++;
        }

        return answer;
    }
}