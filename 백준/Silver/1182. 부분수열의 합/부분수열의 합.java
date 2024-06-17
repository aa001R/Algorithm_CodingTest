import java.io.*;
import java.util.*;

public class Main {
	static int N, S, ans;
	static int [] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		ans = 0;
		powerSet(0, new boolean[N], 0);
		System.out.println(ans);
	}
	private static void powerSet(int cnt, boolean[] isSelected, int selectCnt) {
		if(cnt == N) {
			if(selectCnt <= 0) return;
			int sum = 0;
			for(int i = 0; i < N; i++) {
				if(!isSelected[i]) continue;
				sum += arr[i];
			}
			if(sum == S) ans++;
			return;
		}
		isSelected[cnt] = true;
		powerSet(cnt+1, isSelected, selectCnt+1);
		isSelected[cnt] = false;
		powerSet(cnt+1, isSelected, selectCnt);
	}
}