import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int minArr[][] = new int[2][3], maxArr[][] = new int[2][3];
	static int pre = 0, cur = 1;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws Exception{
		input();
		bw.append(Integer.toString(Math.max(maxArr[pre][0], Math.max(maxArr[pre][1], maxArr[pre][2]))))
		.append(" ")
		.append(Integer.toString(Math.min(minArr[pre][0], Math.min(minArr[pre][1], minArr[pre][2]))));
		bw.flush();
	}
	static void input() throws Exception{
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				maxArr[cur][j] = minArr[cur][j] = Integer.parseInt(st.nextToken());
			}
			updateMax();
			updateMin();
			cur = (cur + 1) % 2;
			pre = (pre + 1) % 2;
		}
	}
	static void updateMax() {
		maxArr[cur][0] = Math.max(maxArr[cur][0]+maxArr[pre][0], maxArr[cur][0]+maxArr[pre][1]);
		maxArr[cur][1] = Math.max(maxArr[cur][1]+maxArr[pre][0], Math.max(maxArr[cur][1]+maxArr[pre][1], maxArr[cur][1]+maxArr[pre][2]));
		maxArr[cur][2] = Math.max(maxArr[cur][2]+maxArr[pre][1], maxArr[cur][2]+maxArr[pre][2]);
	}
	static void updateMin() {
		minArr[cur][0] = Math.min(minArr[cur][0]+minArr[pre][0], minArr[cur][0]+minArr[pre][1]);
		minArr[cur][1] = Math.min(minArr[cur][1]+minArr[pre][0], Math.min(minArr[cur][1]+minArr[pre][1], minArr[cur][1]+minArr[pre][2]));
		minArr[cur][2] = Math.min(minArr[cur][2]+minArr[pre][1], minArr[cur][2]+minArr[pre][2]);
	}
}
