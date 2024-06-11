
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int m = in.nextInt();
		boolean[][] arr = new boolean[n][m];
		int min = 64;
		// 배열 입력
		for (int i = 0; i < n; i++) {
			String str = in.next();
			for (int j = 0; j < m; j++) {
				if (str.charAt(j) == 'W') {
					arr[i][j] = true; // W일 때는 true
				} else {
					arr[i][j] = false; // B일 때는 false
				}

			}
		}

		int row = n - 7;
		int col = m - 7;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int end_i = i + 8;
				int end_j = j + 8;
				int cnt = 0;

				boolean TF = arr[i][j]; // 첫 번째 칸의 색

				for (int k = i; k < end_i; k++) {
					for (int l = j; l < end_j; l++) {
						// 올바른 색이 아닐경우 cnt 1 증가
						if (arr[k][l] != TF) {
							cnt++;
						}
						// 다음 칸 색 반전
						TF = (!TF);
					}
					// 다음 줄 색 반적
					TF = !TF;
				}
				// 시작 색이 반대일 경우(64-cnt)와 비교
				cnt = Math.min(cnt, 64 - cnt);

				min = Math.min(min, cnt);
			}
		}
		System.out.println(min);
	}
}