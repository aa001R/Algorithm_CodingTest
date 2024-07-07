import java.util.*;
import java.io.*;

class Main {
	static int N;
	static int[] seq, r_dp, l_dp;
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read();
		seq = new int[N];
		r_dp = new int[N];
		l_dp = new int[N];
		for(int i = 0; i < N; i++) {
			seq[i] = read();
		}
		LIS();
		LDS();
		int max = 0;
		for(int i = 0; i < N; i++) {
			if(max >= r_dp[i] + l_dp[i]) continue;
			max = r_dp[i] + l_dp[i];
		}
		bw.append(Integer.toString(max-1));
		bw.flush();
	}
	// 증가
	private static void LIS() {
		for(int i = 0; i < N; i++) {
			r_dp[i] = 1;
			for(int j = 0; j < i; j++) {
				if(seq[j] < seq[i] && r_dp[i] < r_dp[j] + 1) {
					r_dp[i] = r_dp[j] + 1;
				}
			}
		}
	}
	
	static void LDS() {
		for (int i = N - 1; i >= 0; i--) {
			l_dp[i] = 1;
			for (int j = N - 1; j > i; j--) {
				if (seq[j] < seq[i] && l_dp[i] < l_dp[j] + 1) {
					l_dp[i] = l_dp[j] + 1;	
				}
			}
		}
	}

	public static int read() throws Exception{
		int n = 0;
        int cur;
        boolean isNumber = false;
        while(true){
            cur = System.in.read();
            if(cur <= 32){
            	if(isNumber) return n;
            }
            else{
            	isNumber = true;
            	n = (n<<3)+(n<<1)+(cur&15);
            }
        }
    }
}