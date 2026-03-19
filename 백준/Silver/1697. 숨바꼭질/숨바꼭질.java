import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int []dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		dp = new int[100_001];
		dp[N] = 1;

		//bfs
		Queue<Integer> que = new ArrayDeque<>();
		que.offer(N);
		while(!que.isEmpty()) {
			int curr = que.poll();
			if(curr == K) break;
			if((curr+1) <= 100000 && dp[curr+1] == 0) {
				dp[curr+1] = dp[curr]+1;
				que.offer(curr+1);
			}
			if((curr*2) <= 100000 && dp[curr*2] == 0) {
				dp[curr*2] = dp[curr]+1;
				que.offer(curr*2);
			}
			if((curr-1) >= 0 && dp[curr-1] == 0) {
				dp[curr-1] = dp[curr]+1;
				que.offer(curr-1);
			}
		}
		
		bw.write(Integer.toString(dp[K]-1));
		bw.flush();
	}

}