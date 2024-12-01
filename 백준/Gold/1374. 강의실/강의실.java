import java.io.*;
import java.sql.Time;
import java.util.*;

class Lecture implements Comparable<Lecture>{
	int num, startT, endT;

	@Override
	public int compareTo(Lecture t){
		if (this.startT == t.startT) return this.endT - t.endT;
		return this.startT - t.startT;
	}

	public Lecture(int num, int startT, int endT) {
		this.num = num;
		this.startT = startT;
		this.endT = endT;
	}
}

public class Main{
	public static void main(String[] args)throws IOException {
		int n = read();
		List<Lecture> lectures = new ArrayList<>();
		for (int i = 0; i < n; i++){
			int num = read();
			int startT = read();
			int endT = read();
			lectures.add(new Lecture(num, startT, endT));
		}
		Collections.sort(lectures); // 빨리 시작하는 강의(>빨리끝나는 강의) 정렬

		PriorityQueue<Integer> q = new PriorityQueue<>();
		int max = 1;

		for (int i = 0; i < n; i++){
			// 비어있는 강의실 제거
			while (!q.isEmpty() && q.peek() <= lectures.get(i).startT){
				q.poll();
			}
			// 강의실 추가
			q.offer(lectures.get(i).endT);
			max = Math.max(max, q.size());
		}
		System.out.println(max);
	}

	static int read() throws IOException {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((cur = System.in.read()) > 32){
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? -n : n;
	}
}
