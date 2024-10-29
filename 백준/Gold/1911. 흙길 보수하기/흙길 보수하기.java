import java.io.*;
import java.util.*;

class Info implements Comparable<Info> {
	int s, e;
	public Info(int s, int e) {
		this.s = s;
		this.e = e;
	}
	@Override
	public int compareTo(Info other) {
		return this.s - other.s; // 시작 위치 기준으로 정렬
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		int N = read();    // 웅덩이 개수
		int L = read();    // 널빤지 길이
		PriorityQueue<Info> pq = new PriorityQueue<>();    // 정렬된 웅덩이 정보

		for (int i = 0; i < N; i++) {
			int s = read();
			int e = read();
			pq.offer(new Info(s, e));
		}

		int result = 0;    // 널빤지 개수
		int fill = 0;    // 현재 널빤지가 덮은 최대 위치

		while (!pq.isEmpty()) {
			Info cur = pq.poll();
			// 현재 웅덩이가 이미 덮인 경우 스킵
			if (cur.e <= fill) continue;
			// 현재 널빤지로 덮어야 부터 시작
			if (fill < cur.s) fill = cur.s;
			// 필요한 널빤지 개수 계산
			int need = (cur.e - fill + L - 1) / L;
			result += need;
			// fill 위치 갱신
			fill += need * L;
		}
		System.out.println(result);
	}

	public static int read() throws IOException {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) {
			n = System.in.read() & 15;
		}
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? ~n : n;
	}
}
