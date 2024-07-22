import java.util.*;
import java.io.*;

class Main {
	static int balloon, staffCnt;
	static ArrayList <Long> balloonTime;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
		staffCnt = Integer.parseInt(st.nextToken());
		balloon = Integer.parseInt(st.nextToken());
		balloonTime = new ArrayList<>(staffCnt);
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < staffCnt; i++) {
			balloonTime.add(Long.parseLong(st.nextToken()));
		}
		
		Collections.sort(balloonTime);
		bw.append(Long.toString(binarySearch(0L, balloonTime.get(staffCnt-1) * balloon+1)));
		bw.flush();
	}
	private static long binarySearch(long left, long right) {
		while(left <= right) {
			long mid = (left + right) / 2;
			long balloonCnt = blowUp(mid);
			if(balloonCnt < balloon) { left = mid + 1; }
			else { right = mid - 1; }
		}
		return left;
	}
	private static long blowUp(long mid) {
		long sum = 0;
		for(long t : balloonTime) {
			sum += (mid / t);
			if(sum >= balloon) break;
		}
		return sum;
	}
}