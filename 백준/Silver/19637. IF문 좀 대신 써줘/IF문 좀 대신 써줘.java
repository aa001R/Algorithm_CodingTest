import java.util.*;
import java.io.*;

class Main {
	static class Rank{
		String title;
		int lowScore;
		public Rank(String title, int lowScore) {
			this.title = title;
			this.lowScore = lowScore;
		}
	}
	static Rank [] rank;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int rankTypeCnt = Integer.parseInt(st.nextToken());
		int characterCnt = Integer.parseInt(st.nextToken());
		rank = new Rank[rankTypeCnt];
		for(int i = 0; i < rankTypeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			String title = st.nextToken();
			int lowScore = Integer.parseInt(st.nextToken());
			rank[i] = new Rank(title,lowScore);
		}
		for(int ch = 0; ch < characterCnt; ch++) {
			int power = Integer.parseInt(br.readLine());
			bw.append(lowerBound(power)); bw.newLine();
		}
		bw.flush();
	}
	private static String lowerBound(int power) {
		int left = 0;
		int right = rank.length;
		while(left < right) {
			int mid = (left + right) / 2;
			if(rank[mid].lowScore >= power) {
				right = mid;
			}else {
				left = mid+1;
			}
		}
		if(right == rank.length) return rank[rank.length-1].title;
		return rank[right].title;
	}
}