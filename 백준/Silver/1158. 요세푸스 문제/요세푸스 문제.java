import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Queue<Integer> que = new ArrayDeque<>();
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int cnt = 0;
		for(int i = 1; i <= N; i++) {
			que.offer(i);
		}
		sb.append("<");
		while(que.size() > 1) {
			int person = que.poll();
			cnt++;
			if(cnt != K) {
				que.offer(person);
				continue;
			}
			sb.append(person).append(", ");
			cnt = 0;
		}
		sb.append(que.poll()).append(">");
		System.out.print(sb);
	}

}
