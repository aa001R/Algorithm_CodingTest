import java.io.*;

public class Main {
    static int[][] coord = {
        {1, 0}, // 0
        {0, 3}, // 1
        {1, 3}, // 2
        {2, 3}, // 3
        {0, 2}, // 4
        {1, 2}, // 5
        {2, 2}, // 6
        {0, 1}, // 7
        {1, 1}, // 8
        {2, 1}, // 9
    };

    static int dist(int a, int b) {
        return Math.abs(coord[a][0] - coord[b][0])
             + Math.abs(coord[a][1] - coord[b][1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] timeStr = br.readLine().split(":");
        int targetH = Integer.parseInt(timeStr[0]);
        int targetM = Integer.parseInt(timeStr[1]);

        int minEffort = Integer.MAX_VALUE;
        int bestH = -1, bestM = -1;

        for (int h = targetH; h <= 99; h += 24) {
            for (int m = targetM; m <= 99; m += 60) {
                int d1 = h / 10, d2 = h % 10;
                int d3 = m / 10, d4 = m % 10;
                int effort = dist(d1, d2) + dist(d2, d3) + dist(d3, d4);
                if (effort < minEffort) {
                    minEffort = effort;
                    bestH = h;
                    bestM = m;
                }
            }
        }

        bw.write(String.format("%02d:%02d%n", bestH, bestM));
        bw.flush();
    }
}