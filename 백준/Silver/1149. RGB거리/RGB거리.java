import java.io.*;
import java.util.*;

public class Main {
	// i 집을 RGB 각각으로 칠할 때 최소 비용 (이전 집을 현재 칠하지 않은 색으로 칠할 때 가장 적은 비용인 경우를 계속 저장)
	//-> R로 칠할 때 vs G로 칠할 때 vs B로 칠할 때 비용 => 이 중 가장 적은 비용 구하기 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int [][] home = new int[N][3];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			home[i][0] = Integer.parseInt(st.nextToken());
			home[i][1] = Integer.parseInt(st.nextToken());
			home[i][2] = Integer.parseInt(st.nextToken());
		}
		for(int i = 1; i < N; i++) {
			home[i][0] += Math.min(home[i-1][1], home[i-1][2]);
			home[i][1] += Math.min(home[i-1][0], home[i-1][2]);
			home[i][2] += Math.min(home[i-1][0], home[i-1][1]);
		}
		bw.write(Integer.toString(Math.min(home[N-1][0], Math.min(home[N-1][1], home[N-1][2]))));
		bw.flush();
	}

}