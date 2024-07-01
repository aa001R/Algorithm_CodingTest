import java.util.*;
import java.io.*;

class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static boolean[][] dp;
	public static void main(String[] args) throws IOException {
		 int n = Integer.parseInt(br.readLine());
	        int []arr = new int[n + 1];
	        dp = new boolean[n + 1][n + 1];

	        StringTokenizer st = new StringTokenizer(br.readLine());
	        for(int i = 1 ; i <= n; i++)
	            arr[i] = Integer.parseInt(st.nextToken());

	        solve(arr, n);
	        int m = Integer.parseInt(br.readLine());

	        for(int i = 0 ; i < m; i++){
	            st = new StringTokenizer(br.readLine());
	            int start = Integer.parseInt(st.nextToken());
	            int end = Integer.parseInt(st.nextToken());
	            if(dp[start][end]) bw.append("1\n");
	            else bw.append("0\n");
	        }
		bw.flush();
	}	
	public static void solve(int[] arr, int n){
        for(int i = 1; i <= n; i++) //팰린드롬 길이 1
            dp[i][i] = true;

        for(int i = 1; i <= n - 1; i++) //팰린드롬 길이 2
            if(arr[i] == arr[i + 1]) dp[i][i + 1]= true;

        for(int i = 2; i < n; i++){ //팰린드롬 길이 3 이상
            for(int j = 1; j <= n - i; j++){
                if(arr[j] == arr[j + i] && dp[j + 1][j + i - 1]) //시작 == 끝 && 시작과 끝 사이 팰린드롬 달성 => 전체 팰린드롬
                    dp[j][j + i] = true;
            }
        }
    }
}