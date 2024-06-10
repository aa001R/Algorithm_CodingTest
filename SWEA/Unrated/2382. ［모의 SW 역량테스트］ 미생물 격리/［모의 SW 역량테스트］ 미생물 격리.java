import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

	// X, 상 1, 하 2, 좌 3, 우 4
	private static final int[] dx = { 0, -1, 1, 0, 0 };
	private static final int[] dy = { 0, 0, 0, -1, 1 };

	private static final int UP = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;

	private static class Crowd implements Comparable<Crowd> {
		public int group;
		public int x;  // 행
		public int y;  // 열
		public int cnt;  // 미생물 수
		public int dir;  // 이동 방향

		public Crowd(int x, int y, int cnt, int dir) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;

			this.group = x * N + y;  // 식별 가능한 숫자로 그룹의 번호로 사용
		}

		@Override
		public String toString() {
			return "Crowd [group=" + group + ", x=" + x + ", y=" + y + ", cnt=" + cnt + ", dir=" + dir + "]";
		}

		@Override
		public int compareTo(Crowd o) {
			// group 기준 오름차순 정렬, cnt 기준 내림차순 정렬
			if (this.group == o.group) {
				return o.cnt - this.cnt;
			}

			return this.group - o.group;
		}

	}

	private static int N;  // 구역 크기
	private static int M;  // 격리 시간
	private static int K;  // 미생물 개수

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		// 입력파일 읽어들이기
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 입력파일 객체화
		int T;
		T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			String[] split = in.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			M = Integer.parseInt(split[1]);
			K = Integer.parseInt(split[2]);

			List<Crowd> crowds = new ArrayList<>();
			for (int k = 0; k < K; k++) {
				split = in.readLine().split(" ");
				int x = Integer.parseInt(split[0]);
				int y = Integer.parseInt(split[1]);
				int cnt = Integer.parseInt(split[2]);
				int dir = Integer.parseInt(split[3]);

				crowds.add(new Crowd(x, y, cnt, dir));
			}

			// 구현
			for (int m = 0; m < M; m++) {
				for (int i = crowds.size() - 1; i >= 0; i--) {

					// 1. 이동
					Crowd crowd = crowds.get(i);
					crowd.x += dx[crowd.dir];
					crowd.y += dy[crowd.dir];
					crowd.group = crowd.x * N + crowd.y;

					// 2. 약품이 칠해진 셀에 도착하면
					if (crowd.x == 0 || crowd.y == 0 || crowd.x == N - 1 || crowd.y == N - 1) {
						// 2-1. 군집 내 미생물의 절반이 죽고
						crowd.cnt /= 2;

						// 2-2. 이동 방향이 반대로 바뀜
						if (crowd.dir == UP) {
							crowd.dir = DOWN;
						}
						else if (crowd.dir == DOWN) {
							crowd.dir = UP;
						}
						else if (crowd.dir == LEFT) {
							crowd.dir = RIGHT;
						}
						else if (crowd.dir == RIGHT) {
							crowd.dir = LEFT;
						}

						// 2-3. 미생물 수가 0이면 군집이 사라짐
						if (crowd.cnt == 0) {
							crowds.remove(i);
						}
					}
				}

				// 3. group 기준 오름차순 정렬, cnt 기준 내림차순 정렬
				Collections.sort(crowds);

				// 4. 이동 후 두개 이상의 군집이 한 셀에 모이는 경우 군집들이 합쳐지게 된다.
				// 이동 방향은 군집들 중에서 미생물 수가 가장 많은 군집에 따라간다.
				for (int i = crowds.size() - 1; i >= 1; i--) {
					Crowd big = crowds.get(i - 1);
					Crowd small = crowds.get(i);

					// 같은 group이면 합치지
					if (big.group == small.group) {
						big.cnt += small.cnt;
						crowds.remove(i);
					}
				}
			}

			// 5. 남은 미생물 수를 구하기
			int cnt = 0;
			for (Crowd crowd : crowds) {
				cnt += crowd.cnt;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}