import java.io.*;

public class Main {
	static int N, min;
	static int[] coins;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		coins = new int[N];
		for(int i = 0; i < N; i++) {
			String input = br.readLine(); int coin = 1;
			for(int j = 0; j < N; j++) {
				if (input.charAt(j) == 'T') {
					coins[i] |= coin;
				}
				coin *= 2;
			}
		}
		min = Integer.MAX_VALUE;
		reverse(1);
		bw.write(Integer.toString(min));
		bw.flush();
	}

	public static void reverse(int index){
		if(index == N+1){
			int sum = 0;
			for (int col = 1; col <= (1 << (N - 1) ) ; col *= 2) {
				int cnt = 0;
				for (int row = 0; row < N ; row++) {
					if((col & coins[row]) != 0 ) cnt++;
				}
				sum += Math.min(cnt, N-cnt);
			}
			min = Math.min(min, sum);
			return;
		}

		reverse(index + 1);
		coins[index-1] = ~coins[index-1]; // 행 뒤집기
		reverse(index + 1);
	}
}
