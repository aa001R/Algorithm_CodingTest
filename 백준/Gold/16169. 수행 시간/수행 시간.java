import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int N = read();

		List<List<Integer>> grade = new ArrayList<>(); // 등급
		List<List<Integer>> arr = new ArrayList<>(); //연결 관계 arr.get(U) => U에서 갈 수 있는 다음 강의
		int[] operTime = new int[N]; // 각 작업 시간
		int[] indegree = new int[N]; // 각 강의 진입 차수 : 진입 차수가 0인 강의는 선행 강의 없이 시작
		int[] ans = new int[N]; // 각 강의의 총 작업 시간 (ans[i]는 i번째 강의가 끝나기까지 걸리는 최소 시간)

		// 배열 초기화
		for (int i = 0; i < N; i++) {
			grade.add(new ArrayList<>());
			arr.add(new ArrayList<>());
		}

		for (int i = 0; i < N; i++) {
			int curG = read() - 1; // 등급은 0부터 시작
			int curT = read();
			grade.get(curG).add(i); // 해당 등급에 컴퓨터 번호 추가
			operTime[i] = curT; // 해당 강의의 작업 시간
		}

		// 강의 간 관계 정의
		for (int i = 0; i < N - 1; i++) {
			// i번째 등급의 강의들과 i+1번째 등급의 강의들 간 연결 생성
			List<Integer> curr = grade.get(i);   // 현재 등급의 강의 목록
			List<Integer> next = grade.get(i + 1); // 다음 등급의 강의 목록

			for (int U : curr) {
				for (int V : next) {
					arr.get(U).add(V); // U -> V 연결
					indegree[V]++;     // V의 진입 차수 증가
				}
			}
		}

		// 큐를 사용한 위상 정렬 준비
		ArrayDeque<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			if (indegree[i] == 0) { // 가장 낮은 등급
				q.offer(new int[]{i, operTime[i]}); // {노드 번호, 작업 시간}
				ans[i] = operTime[i];              // 초기 작업 시간 설정
			}
		}

		// 위상 정렬 수행
		while (!q.isEmpty()) {
			int[] current = q.poll();
			int currNum = current[0];  // 현재 노드 번호
			int currTime = current[1]; // 현재까지의 총 시간

			for (int nextNum : arr.get(currNum)) {
				// U에서 V로 이동 시간 계산 (전송시간 + 이전 동작 시간)
				int transTime = (currNum - nextNum) * (currNum - nextNum) + currTime;
				// V 동작이 종료하는 걸리는 총 시간 갱신
				ans[nextNum] = Math.max(ans[nextNum], transTime + operTime[nextNum]);
				// 진입 차수 감소 및 큐 추가
				indegree[nextNum]--;
				if (indegree[nextNum] == 0) { // 진입 차수 없음 > 다음 진입 시작 num
					q.offer(new int[]{nextNum, ans[nextNum]});
				}
			}
		}

		// 결과 출력: ans 배열 중 최댓값
		int maxTime = Arrays.stream(ans).max().orElse(0);
		System.out.println(maxTime);
	}

	static int read() throws IOException {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) { n = System.in.read() & 15; }
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative? -n : n;
	}
}
