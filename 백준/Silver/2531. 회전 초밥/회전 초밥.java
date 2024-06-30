import java.util.*;
import java.io.*;

class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int dishCnt = Integer.parseInt(st.nextToken());
		int susiTypeCnt = Integer.parseInt(st.nextToken());
		int eatenPlatesCnt = Integer.parseInt(st.nextToken());
		int coupon = Integer.parseInt(st.nextToken());
		int [] susiDish = new int[dishCnt];
		for(int i = 0; i < dishCnt; i++) {
			susiDish[i] = Integer.parseInt(br.readLine());
		}
		
		int [] eatSusi = new int[susiTypeCnt+1];
		eatSusi[coupon] = 1;
		int max = 1;
		for(int s = 0; s < eatenPlatesCnt; s++) {
			eatSusi[susiDish[s]]++;
			if(eatSusi[susiDish[s]] == 1) {
				max++;
			}
		}
		int startIdx = 0;
		
		int cur = max;
		for(int nextIdx = startIdx+eatenPlatesCnt; startIdx < dishCnt; nextIdx = (nextIdx+1)%dishCnt, startIdx++) {
			eatSusi[susiDish[startIdx]]--;
			if(eatSusi[susiDish[startIdx]] < 1) { cur--; }
			eatSusi[susiDish[nextIdx]]++;
			if(eatSusi[susiDish[nextIdx]] == 1) { cur++; }
			if(max < cur) { max = cur; }
		}
		bw.write(Integer.toString(max));
		bw.flush();
	}	
}