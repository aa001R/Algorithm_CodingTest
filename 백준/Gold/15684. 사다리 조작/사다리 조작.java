import java.io.*;
import java.util.*;

public class Main {
	static int N, M, H;
	static boolean[][] ladder;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		ladder = new boolean[H][N-1];
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			ladder[a][b] = true;
		}
		cnt = Integer.MAX_VALUE;
		addLadder(0, 0, 0, new int[4][2]);
		if(cnt == Integer.MAX_VALUE) {
			System.out.println("-1");
		}else {
			System.out.println(cnt);
		}
		
	}
	
	static void addLadder(int addCnt, int startH, int startN, int[][]select) {
		if(cnt <= addCnt) {
			return;
		}
		if(addCnt > 3) {
			return;
		}
		
		if(checkLadder()) {
			cnt = Math.min(cnt, addCnt);
			return;
		}
		int n = startN;
		for(int h = startH; h < H; h++) {
			for(; n < N-1; n++) {
				if(ladder[h][n]) continue;
				if(isIn(h, n-1) && ladder[h][n-1]) continue;
				if(isIn(h, n+1) && ladder[h][n+1]) continue;
				ladder[h][n] = true;
				select[addCnt][0] = h; select[addCnt][1] = n;
				addLadder(addCnt+1, h, n+1, select);
				ladder[h][n] = false;
			}
			n = 0;
		}
	}
	
	static boolean checkLadder() {
		for (int n = 0; n < N; n++) {
			int h = 0;
			int currN = n;
			while(h < H) {
				if(isIn(h, currN) && ladder[h][currN]) {
					currN++;
				}
				else if(isIn(h, currN-1) && ladder[h][currN-1]) {
					currN--;
				}
				h++;
			}
			if(currN != n) return false;
		}
		return true;
	}
	
	static boolean isIn(int r, int c) {
		return (0<=r&&r<H&&0<=c&&c<N-1);
	}
}
