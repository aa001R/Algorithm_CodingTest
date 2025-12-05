import java.util.*;

class Solution {

    // 격자가 최대 4x4이므로 16칸을 bitmask로 관리하기 위한 함수
    private int bit(int r, int c, int m) {
        return 1 << (r * m + c);
    }

    public int solution(int[][] maze) {

        int n = maze.length;
        int m = maze[0].length;

        // 빨강/파랑 시작점과 도착점 좌표
        int sr = -1, sc = -1, er = -1, ec = -1;
        int br = -1, bc = -1, eb = -1, ec2 = -1;

        // 위치 파싱
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1) { sr = i; sc = j; }
                else if (maze[i][j] == 3) { er = i; ec = j; }
                else if (maze[i][j] == 2) { br = i; bc = j; }
                else if (maze[i][j] == 4) { eb = i; ec2 = j; }
            }
        }

        // BFS 큐 상태: {rr, rc, br, bc, redMask, blueMask, turn}
        Queue<int[]> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        int redMask = bit(sr, sc, m);
        int blueMask = bit(br, bc, m);

        String initKey = sr+","+sc+","+br+","+bc+","+redMask+","+blueMask;
        q.add(new int[]{sr, sc, br, bc, redMask, blueMask, 0});
        visited.add(initKey);

        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int rr = cur[0], rc = cur[1];
            int br2 = cur[2], bc2 = cur[3];
            int rMask = cur[4], bMask = cur[5];
            int turn = cur[6];

            // 종료 조건: 두 말 모두 도착
            if (rr == er && rc == ec && br2 == eb && bc2 == ec2) {
                return turn;
            }

            // 4x4 이동 조합 탐색
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {

                    // 빨강 이동
                    int nrr = rr;
                    int nrc = rc;
                    if (!(rr == er && rc == ec)) { // 도착한 경우 고정
                        nrr = rr + dr[i];
                        nrc = rc + dc[i];
                        if (nrr < 0 || nrr >= n || nrc < 0 || nrc >= m) continue;
                        if (maze[nrr][nrc] == 5) continue;
                        if ((rMask & bit(nrr, nrc, m)) != 0) continue;
                    }

                    // 파랑 이동
                    int nbr = br2;
                    int nbc = bc2;
                    if (!(br2 == eb && bc2 == ec2)) { // 도착한 경우 고정
                        nbr = br2 + dr[j];
                        nbc = bc2 + dc[j];
                        if (nbr < 0 || nbr >= n || nbc < 0 || nbc >= m) continue;
                        if (maze[nbr][nbc] == 5) continue;
                        if ((bMask & bit(nbr, nbc, m)) != 0) continue;
                    }

                    // 두 말이 같은 칸 이동 금지
                    if (nrr == nbr && nrc == nbc) continue;

                    // 위치 swap 금지
                    if (nrr == br2 && nrc == bc2 && nbr == rr && nbc == rc) continue;

                    int newRMask = rMask | bit(nrr, nrc, m);
                    int newBMask = bMask | bit(nbr, nbc, m);

                    String key = nrr+","+nrc+","+nbr+","+nbc+","+newRMask+","+newBMask;
                    if (!visited.contains(key)) {
                        visited.add(key);
                        q.add(new int[]{
                                nrr, nrc, nbr, nbc, newRMask, newBMask, turn + 1
                        });
                    }
                }
            }
        }

        return 0;
    }
}
