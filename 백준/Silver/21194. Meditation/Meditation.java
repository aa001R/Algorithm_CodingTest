import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for(int i = 0; i < n; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		int sum = 0;
		for(int i = 0; i < k; i++) {
			sum += pq.poll();
		}
		bw.write(Integer.toString(sum));
		bw.flush();
	}
}