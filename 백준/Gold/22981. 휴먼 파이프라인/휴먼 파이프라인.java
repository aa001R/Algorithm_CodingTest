import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		
		ArrayList <Long> workPerMinute = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			workPerMinute.add(Long.parseLong(st.nextToken()));
		}
		Collections.sort(workPerMinute);
		
		long maxTeamWork = 0;
		for(int i = 0; i < N-1; i++) {
			maxTeamWork = Math.max(maxTeamWork,  workPerMinute.get(0) * (i+1) + workPerMinute.get(i+1) * (N - (i+1)));
		}
		bw.append(Long.toString(K % maxTeamWork == 0 ? K / maxTeamWork : K / maxTeamWork + 1));
		bw.flush();
	}
}