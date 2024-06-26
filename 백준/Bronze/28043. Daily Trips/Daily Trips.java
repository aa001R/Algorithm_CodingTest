import java.util.*;
import java.io.*;

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException
	{
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int go = 0; go < 2; go++) {
				switch (st.nextToken()) {
				case "Y":
					if(go % 2 == 0) { // home -> work
						H--; W++;
					}else { //work -> home
						H++; W--;
					}
					bw.append("Y ");
					break;
				case "N":
					if(go % 2 == 0 && W == 0) { // home -> work
						H--; W++; bw.append("Y ");
					}else if(go % 2 != 0 && H == 0){ //work -> home
						H++; W--; bw.append("Y ");
					}else {
						bw.append("N ");
					}
					break;
				}
			}
			bw.newLine();
		}	
		bw.flush();
	}
}