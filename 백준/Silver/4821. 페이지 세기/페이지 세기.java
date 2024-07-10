import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		while(true) {
			int totalPage = Integer.parseInt(br.readLine());
			if(totalPage == 0) break;
			int page [] = new int[totalPage+1];
			st = new StringTokenizer(br.readLine(), ",");
			int pageCnt = 0;
			while(st.hasMoreTokens()) {
				String [] range = st.nextToken().split("-");
				for(int r = Integer.parseInt(range[0]); r <= Integer.parseInt(range[range.length-1]); r++) {
					if(r > totalPage) break;
					if(page[r] != 0) continue;
					page[r]++;
					pageCnt++;
				}
			}
			bw.append(Integer.toString(pageCnt));
			bw.newLine();
		}
		bw.flush();
	}
}