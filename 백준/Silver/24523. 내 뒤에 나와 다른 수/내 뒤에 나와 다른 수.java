import java.util.*;
import java.io.*;

class Main {
	static int N, arrNum[], diffIdx[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		arrNum = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int now = Integer.parseInt(st.nextToken());
		for(int i = 1; i < N; i++) {
			int cur = Integer.parseInt(st.nextToken());
			if(now != cur) {
				now = cur;
				arrNum[i] = arrNum[i-1] + 1; 
			}else {
				arrNum[i] = arrNum[i-1];
			}
		}
		for (int i = 0; i < N; i++) {
			bw.append(Integer.toString(binarySearch(i))).append(" ");
        }
		bw.flush();
	}

	private static int binarySearch(int idx) {
		int start = idx;
		int end = N;
		while(start < end) {
			int mid = (start + end) / 2;
			if(arrNum[idx] < arrNum[mid]) {
				end = mid;
			}else{
				start = mid + 1;
			}
		}
		if(end == N) return -1;
		return end+1;
	}
}