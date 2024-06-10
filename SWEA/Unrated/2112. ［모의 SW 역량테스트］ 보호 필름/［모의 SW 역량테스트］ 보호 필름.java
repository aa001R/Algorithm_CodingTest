import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
 
public class Solution {
    static int D, W, K;
    static int min;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            String[][] film = new String[D][W];
            for (int d = 0; d < D; d++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int w = 0; w < W; w++) {
                    film[d][w] = st.nextToken();
                }
            }
            min = K+1;
            dfs(0, 0, film);
            sb.append("#").append(test).append(" ").append(min).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
 
    private static void dfs(int startIdx, int k, String[][] film) {
        if(min <= k) {
            return;
        }
        if(inspect(film)) {
            min = k;
            return;
        }
        if(k == K && min > k) {
            min = k;
            return;
        }
        for (int i = startIdx; i < D; i++) {
            ArrayDeque<Integer> changCol = new ArrayDeque<>();
            // A 약물처리 1 -> 0
            for (int j = 0; j < W; j++) {
                if(film[i][j].equals("1")) {
                    film[i][j] = "0";
                    changCol.offer(j);
                }
            }
            dfs(i+1, k+1, film);
            // 약물복원
            while(!changCol.isEmpty()) {
                int colIdx = changCol.poll();
                film[i][colIdx] = "1";
            }
             
            // B 약물 처리 0->1
            for (int j = 0; j < W; j++) {
                if(film[i][j].equals("0")) {
                    film[i][j] = "1";
                    changCol.offer(j);
                }
            }
            dfs(i+1, k+1, film);
            // 약물복원
            while(!changCol.isEmpty()) {
                int colIdx = changCol.poll();
                film[i][colIdx] = "0";
            }
        }
    }
 
    private static boolean inspect(String[][] film) {
        for(int w = 0; w < W; w++) {
            int stack = 1;
            for(int d = 0; d < D-1; d++) {
                if(film[d][w].equals(film[d+1][w])) {
                    stack++;
                } else {
                    stack = 1;
                }
                if(stack == K) {
                    break;
                }
            }
            if(stack != K) {
                return false;
            }
        }
        return true;
    }
}