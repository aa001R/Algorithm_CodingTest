import java.io.*;

public class Main {
	static int N;
	static char[][] coins;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		coins = new char[N][N];
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < N; j++) {
				coins[i][j] = input.charAt(j);
			}
		}

		int min = Integer.MAX_VALUE;
		// bit는 뒤집은 행을 나타내는 부분집합
		for(int bit = 1; bit < (1 << N); bit++) {
			int sum = 0;
			for(int c = 0; c < N; c++) {
				int back = 0;
				for(int r = 0; r < N; r++) { // 열을 기준으로 확인함
					char curr = coins[r][c];
					if((bit & (1 << r)) != 0) curr = reverse(curr); // 해당 행은 뒤집었음
					if(curr == 'T') back++;
				}
				// 행이 뒤집혔든 아니든,
				// back : 해당 열은 안 뒤집음 / N-back : 해당 열도 뒤집음 => 최소값 경우 선택
				sum += Math.min(back, N-back);
			}
			min = Math.min(min, sum);
		}
		bw.write(Integer.toString(min));
		bw.flush();
	}
	public static char reverse(char curr) {
		if(curr == 'T') return 'H';
		return 'T';
	}
}
