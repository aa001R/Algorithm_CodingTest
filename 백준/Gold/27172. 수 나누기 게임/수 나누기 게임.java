import java.util.*;
import java.io.*;

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, score[], player[], min = 1_000_001, max = 0;
	static boolean card[] = new boolean[1_000_001];
	public static void main(String[] args) throws IOException
	{
		N = Integer.parseInt(br.readLine());
		player = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			player[i] = Integer.parseInt(st.nextToken());
			card[player[i]] = true;
			if(max < player[i]) max = player[i];
		}
		score = new int[max+1];
		for (int i = 0; i < N; i++) {
            for (int j = player[i]*2; j <= max; j+=player[i]) {
                if (!card[j]) continue;
                score[player[i]]++; score[j]--;
            }
        }
		
		for(int i = 0; i < N; i++) {
			bw.append(Integer.toString(score[player[i]])).append(" ");
		}
		bw.flush();
	}
}