import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException{		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		for(int testcase = 1; testcase <= 10; testcase++) {
			List<String> cipher = new LinkedList<>();
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			for(int n = 0; n < N; n++) {
				cipher.add(st.nextToken());
			}
			int cmdCnt = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			int x, y;
			for(int i = 0; i < cmdCnt; i++) {
				String cmd = st.nextToken();
				switch (cmd){
				case "I":
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					for(int cnt = 0; cnt < y; cnt++) {
						cipher.add(x+cnt, st.nextToken());
					}
					break;
				case "D":
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					for(int cnt = 0; cnt < y; cnt++) {
						cipher.remove(x+cnt);
					}
					break;
				case "A":
					y = Integer.parseInt(st.nextToken());
					for(int cnt = 0; cnt < y; cnt++) {
						cipher.add(st.nextToken());
					}
					break;
				}
			}
			
			bw.append("#").append(Integer.toString(testcase)).append(" ");
			for(int i = 0; i < 10; i++) {
				bw.append(cipher.get(i)).append(" ");
			}
			bw.newLine();
		}
		bw.flush();
	}
}