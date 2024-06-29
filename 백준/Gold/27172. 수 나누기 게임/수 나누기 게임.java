import java.util.*;
import java.io.*;

class Main {
	static class Node{
		Node next;
		int num;
		public Node(int num, Node next) {
			this.num = num;
			this.next = next;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, score[] = new int[1_000_001], player[];
	static boolean card[] = new boolean[1_000_001];
	static ArrayList<Integer> [] divisor;
	public static void main(String[] args) throws IOException
	{
		N = Integer.parseInt(br.readLine());
		player = new int[N];
		divisor = new ArrayList[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			player[i] = Integer.parseInt(st.nextToken());
			card[player[i]] = true;
			divisor[i] = new ArrayList<>();
		}
		for(int i = 0; i < N; i++) {
			for(int j = 1; j * j <= player[i]; j++) {
				if(player[i] % j == 0 ) divisor[i].add(j);
				if(j*j != player[i] && player[i] % j == 0) divisor[i].add(player[i] / j);
			}
		}
		for(int i = 0; i < N; i++) {
			for(int d : divisor[i]) {
				if(!card[d]) continue;
				score[d]++; score[player[i]]--;
			}
		}
		for(int i = 0; i < N; i++) {
			bw.append(Integer.toString(score[player[i]])).append(" ");
		}
		bw.flush();
	}
}