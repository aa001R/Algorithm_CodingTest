import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		long[] liquid = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			liquid[i] = Long.parseLong(st.nextToken()); // 오름차순으로 입력
		}
		int left =0;
		int right =n-1;
		int minL =0, minR = 0;
		long min = Long.MAX_VALUE;
		while(left<right) {
			long sum = liquid[left]+liquid[right];
			if(min > Math.abs(sum)) {
				min = Math.abs(sum);
				minL = left; minR = right;
			}
			if(sum>=0) {
				right--;	
			}else {
				left++;
			}
		}
		
		bw.append(Long.toString(liquid[minL])).append(" ").append(Long.toString(liquid[minR]));
		bw.newLine();
		bw.flush();
	}

}