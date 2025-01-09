import java.util.*;
import java.io.*;

public class Main {
	static int K;
	static char[] ltgt;
	static char[] stopChars = {'<' , '>'};
	static int[] ans;
	static int last, MAX = 0, MIN = 1;
	static ArrayDeque<Integer> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		K = Integer.parseInt(br.readLine());
		ltgt = new char[K];
		ans = new int[K + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			ltgt[i] = st.nextToken().charAt(0);
		}

		last = 0;
		queue = new ArrayDeque<>();
		for (int i = 9; i >= 0; i--) {
			queue.add(i);
		}
		setInequality(MAX);
		for (int j = 0; j < K+1; j++) {
			bw.append(Integer.toString(ans[j]));
		}
		bw.newLine();

		last = 0;
		queue = new ArrayDeque<>();
		for (int i = 0; i < 10; i++) {
			queue.add(i);
		}
		setInequality(MIN);
		for (int j = 0; j < K+1; j++) {
			bw.append(Integer.toString(ans[j]));
		}
		bw.newLine();

		bw.flush();
	}

	public static void setInequality(int idx) {
		for (int i = 0; i < K; i++) {
			// 0 : < MAX 값 찾기 (큰 값부터 맨 앞에 위치하되 부등호 유지를 위해 범위 측정용("가장 큰 값"> "3 번째 큰 값" < "2번째 큰 값"))
			// 1 : > MIN 값 찾기 (작은 값부터 '' (가장 작은 값 < 3번째로 작은 값 > 2번째로 작은 값))
			if (ltgt[i] == stopChars[idx]) { 
				int count = 0;
				while (i + count < K && ltgt[i + count] == stopChars[idx]) { // 연속 되는 ('<' or '>')  범위 구하기
					count++;
				}
				for (int j = (i + count); j >= i; j--) { // 해당 범위 마지막부터 채우기
					ans[j] = queue.poll();
				}
				i += count; last += count + 1; // 해당 범위는 채웠으니 건너띄기
			} else {
				ans[last] = queue.poll(); last++;
			}
			if (i == K - 1) {
				ans[last] = queue.poll();
			}
		}
	}

}