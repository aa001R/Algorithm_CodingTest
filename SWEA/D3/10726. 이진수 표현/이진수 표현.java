import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException{		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			bw.append("#").append(Integer.toString(testcase)).append(" ");
			if((m & ((1 << n) - 1)) == (1 << n) - 1) {
				bw.append("ON");
			}else {
				bw.append("OFF");
			}
			bw.newLine();
		}
		bw.flush();
	}
}