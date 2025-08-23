import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int n = read();
		PriorityQueue<int []> pq = new PriorityQueue<>(Comparator.comparing(a -> a[1]));
		for (int i = 0; i < n; i++) {
			int home = read(), office = read();
			pq.offer(new int[]{i, home});
			pq.offer(new int[]{i, office});
		}
		int d = read();
		ArrayDeque<int []> q = new ArrayDeque<>();
		int [] person = new int [n];
		int max = 0, sum = 0;
		while(!pq.isEmpty()){
			if(!q.isEmpty() && pq.peek()[1] - q.peek()[1] > d) {
				max = Math.max(max, sum);
				while(!q.isEmpty() && pq.peek()[1] - q.peek()[1] > d) {
					if (person[q.poll()[0]]-- == 2) sum--;
				}
			}
			int[] cur = pq.poll();
			q.offer(cur);
			if(++person[cur[0]] == 2) sum++;
		}
		max = Math.max(max, sum);
		System.out.println(max);
	}

	// 빠른 입력을 위한 read 함수
	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if (isNegative) n = System.in.read() & 15;
		while ((cur = System.in.read()) > 32) n = (n << 3) + (n << 1) + (cur & 15);
		return isNegative ? -n : n;
	}
}
