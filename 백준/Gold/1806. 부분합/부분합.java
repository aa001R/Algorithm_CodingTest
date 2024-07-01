import java.util.*;
import java.io.*;

class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int [] arr = new int[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int minLen = N+1, sum = 0;
		int startIdx = 0, endIdx = 0;
		while(startIdx <= endIdx && endIdx <= N) {
			if(sum >= S) {
				minLen = Math.min(minLen, endIdx - startIdx);
				sum -= arr[startIdx++];
			}else {
				sum += arr[endIdx++];
			}
			if(minLen == 1) break;
		}
		
		if(minLen == N+1) minLen = 0;
		bw.append(Integer.toString(minLen));
		bw.flush();
	}	
}