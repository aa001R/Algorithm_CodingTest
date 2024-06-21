import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int T=Integer.parseInt(br.readLine());
		for(int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine());
			double dogCnt = Double.parseDouble(st.nextToken());
			double foodPerDog = Double.parseDouble(st.nextToken());
			double pricePerFood = Double.parseDouble(st.nextToken());
			bw.append("$").append(String.format("%.2f", pricePerFood*dogCnt*foodPerDog));
			bw.newLine();
		}
		bw.flush();
	}
}