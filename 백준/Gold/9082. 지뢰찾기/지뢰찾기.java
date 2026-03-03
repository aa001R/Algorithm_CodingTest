import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			String numberRow = br.readLine();
			String blockRow  = br.readLine();

			int[] need   = new int[N];
			char[] isMine = new char[N];
			for (int i = 0; i < N; i++) {
				need[i]   = numberRow.charAt(i) - '0';
				isMine[i] = blockRow.charAt(i);
			}

			if (N == 1) {
				bw.append(Integer.toString(need[0])).append("\n");
				continue;
			}
			// 1단계: 인접한 * + # 수가 need와 같으면 모든 # → * 확정
			for (int dir = 0; dir < 2; dir++) {
				int start = (dir == 0) ? 0 : N - 1;
				int end   = (dir == 0) ? N : -1;
				int step  = (dir == 0) ? 1 : -1;

				for (int i = start; i != end; i += step) {
					int mineOrHidden = 0;
					for (int j = i - 1; j <= i + 1; j++) {
						if (j < 0 || j >= N) continue;
						if (isMine[j] == '*' || isMine[j] == '#') mineOrHidden++;
					}
					if (mineOrHidden == need[i]) {
						for (int j = i - 1; j <= i + 1; j++) {
							if (j < 0 || j >= N || isMine[j] == '.') continue;
							isMine[j] = '*';
						}
					}
				}
			}

			// 2단계: greedy - 좌→우로 need만큼 # → * 배치, 나머지 # → .
			for (int i = 0; i < N; i++) {
				int remaining = need[i];
				for (int j = i - 1; j <= i + 1; j++) {
					if (j < 0 || j >= N) continue;
					if (isMine[j] == '*') remaining--;
				}
				for (int j = i - 1; j <= i + 1; j++) {
					if (j < 0 || j >= N || isMine[j] != '#') continue;
					if (remaining > 0) { isMine[j] = '*'; remaining--; }
					else               { isMine[j] = '.'; }
				}
			}

			int totalMines = 0;
			for (char c : isMine) if (c == '*') totalMines++;
			bw.append(Integer.toString(totalMines)).append('\n');
		}
		bw.flush();
	}
}
