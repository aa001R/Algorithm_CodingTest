import java.util.*;
import java.io.*;

class Main {
    static int N, K;
    static int[] time = new int[100001];
    static int minTime = 987654321;
    static int count = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if (N >= K) {
            bw.append(Integer.toString(N-K)); bw.newLine(); bw.append("1");
            bw.flush();
            return;
        }
        bfs();
        bw.append(Integer.toString(minTime)); bw.newLine(); bw.append(Integer.toString(count));
        bw.flush();
    }

    static void bfs() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(N);
        time[N] = 1;
        while (!q.isEmpty()) {
            int now = q.poll();
            if (minTime < time[now]) return; // now 방문 시간이 최소 시간보다 크면 뒤는 더 볼 필요 없음
            for (int i=0; i<3; i++) {
                int next;
                if (i == 0)         next = now + 1;
                else if (i == 1)    next = now - 1;
                else                next = now * 2;
                if (next < 0 || next > 100000) continue;
                if (next == K) {
                    minTime = time[now];
                    count++;
                }
                if (time[next] == 0 || time[next] == time[now] + 1) { // time[next] == 0 -> 첫방문
                    q.add(next);
                    time[next] = time[now] + 1;
                }
            }
        }
    }
}