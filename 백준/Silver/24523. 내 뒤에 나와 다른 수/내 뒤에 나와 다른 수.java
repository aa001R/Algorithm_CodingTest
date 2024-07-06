import java.util.*;
import java.io.*;

class Main {
	static int N, arrNum[], diffIdx[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read();
		arrNum = new int[N];
		int now = read();
		for(int i = 1; i < N; i++) {
			int cur = read(); // 현재 숫자
			if(now != cur) {
				now = cur;
				arrNum[i] = arrNum[i-1] + 1; 
			}else {
				arrNum[i] = arrNum[i-1];
			}
		}
		for (int i = 0; i < N; i++) {
			bw.append(Integer.toString(binarySearch(i))).append(" ");
        }
		bw.flush();
	}

	private static int binarySearch(int idx) {
		int start = idx;
		int end = N;
		while(start < end) {
			int mid = (start + end) / 2;
			if(arrNum[idx] < arrNum[mid]) {
				end = mid;
			}else{
				start = mid + 1;
			}
		}
		if(end == N) return -1;
		return end+1;
	}
	
	public static int read() throws Exception{
        int n = 0;
        int cur;
        boolean isNumber = false;
        boolean isNegative = false;
        while(true){
            cur = System.in.read();
            if(cur == '-'){
                isNegative = true;
            }
            else if(cur <= 32){
                if(isNumber){ return isNegative ? -n : n; }
            }
            else{
                isNumber = true;
                n = (n<<3) + (n<<1) + (cur&15);
                // n의 10배(8배+2배) + 새로운 숫자(c&15)로 n = 10 * n + c - 48 연산과 동일
                // 0 -> 아스키코드 48 110000~111111에서 110000을 제외한 0000~1111값만을 확인
            }
        }
    }
}