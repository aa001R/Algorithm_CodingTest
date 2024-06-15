import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException{		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= T; testcase++) {
			int n = Integer.parseInt(br.readLine());
			int cnt = 0;
			int check = 1 << 10;
			
			while((check & ((1 << 10) - 1)) != ((1 << 10) - 1)) {
				cnt++;
				int order = n * cnt;
				while(order > 0) {
					check |= (1 << (order % 10));
					order /= 10;
				}
			}
			bw.append("#").append(Integer.toString(testcase)).append(" ")
			.append(Integer.toString(cnt * n));
			bw.newLine();
		}
		bw.flush();
	}

}