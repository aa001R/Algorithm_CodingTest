import java.io.*;
import java.util.*;

public class Main {
	static class Walk{
		int position, time;
		public Walk(int position, int time) {
			this.position = position;
			this.time = time;
		}
	}
	static int minT, cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		bfs(start, end);
		bw.append(Integer.toString(minT)); bw.newLine();
		bw.append(Integer.toString(cnt)); bw.newLine();
		bw.flush();
	}
	private static void bfs(int start, int end) {
		int [] time = new int[100_001];
		Arrays.fill(time, 100_003);
		ArrayDeque<Walk> q = new ArrayDeque<>();
		time[start] = 0;
		q.offer(new Walk(start, time[start]));
		
		while(!q.isEmpty()) {
			Walk cur = q.poll();
			if(cur.position == end) {
				if(cur.time > time[end]) continue;
				if(cur.time == time[end]) cnt++;
				if(cur.time < time[end]) cnt = 1;
				time[end] = cur.time;
				continue;
			}
			if(cur.position+1 <= 100_000 && time[cur.position+1] >= cur.time+1) {
				time[cur.position+1] = cur.time+1;
				q.offer(new Walk(cur.position+1, cur.time+1));
			}
			if(cur.position-1 >= 0 && time[cur.position-1] >= cur.time+1) {
				time[cur.position-1] = cur.time+1;
				q.offer(new Walk(cur.position-1, cur.time+1));
			}
			if(cur.position*2 <= 100_000 && time[cur.position*2] >= cur.time+1) {
				time[cur.position*2] = cur.time+1;
				q.offer(new Walk(cur.position*2, cur.time+1));
			}
		}
		minT = time[end];
	}
}
