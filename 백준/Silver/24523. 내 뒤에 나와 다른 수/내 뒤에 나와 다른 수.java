import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int [] arrNum = new int[N];
		int [] diffIdx = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for(int i = 0; i < N; i++) {
			arrNum[i] = Integer.parseInt(st.nextToken());
			while(!stack.isEmpty() && arrNum[stack.peek()] != arrNum[i]) {
				int idx = stack.pop();
				diffIdx[idx] = i+1;
			}
			stack.push(i);
		}
		while(!stack.isEmpty()) {
			int idx = stack.pop();
			diffIdx[idx] = -1;
		}
		for(int idx : diffIdx) {
			bw.append(Integer.toString(idx)).append(" ");
		}
		bw.flush();
	}
}