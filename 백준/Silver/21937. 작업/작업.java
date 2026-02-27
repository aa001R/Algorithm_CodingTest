import java.io.*;

public class Main {
	static class Node {
		int no;
		Node next;
		Node(int no, Node next) {
			this.no = no;
			this.next = next;
		}
	}
	static Node[] nodes;
	static int cnt;
	static boolean [] worked;
	public static void main(String[] args) throws IOException {
		int N = read(), M = read();
		nodes = new Node[N+1];
		worked = new boolean[N+1];
		cnt = 0;
		for (int i = 0; i < M; i++) {
			int a = read(), b = read();
			nodes[b] = new Node(a, nodes[b]);
		}
		int workN = read();
		dfs(workN);
		System.out.println(cnt);
	}

	public static void dfs(int no) {
		for(Node tmp = nodes[no]; tmp != null; tmp = tmp.next){
			if(worked[tmp.no]) continue;
			worked[tmp.no] = true;
			dfs(tmp.no);
			cnt++;
		}
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