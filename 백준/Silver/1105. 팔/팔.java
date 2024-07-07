import java.util.*;
import java.io.*;

class Main {
	static int LEFT = 0, RIGHT = 1;
	static int [] eightCnt = new int[2];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String L = read(LEFT);
		String R = read(RIGHT);
		int cnt = 0;
		if(eightCnt[LEFT] == 0 || eightCnt[RIGHT] == 0 || L.length() != R.length()) {
			bw.append("0");
			bw.flush();
			return;
		}
		for(int i = 0; i < L.length(); i++) {
			if(L.charAt(i) == '8' && L.charAt(i) == R.charAt(i)) cnt++;
			else if(L.charAt(i) != R.charAt(i)) break;
		}
		bw.append(Integer.toString(cnt));
		bw.flush();
	}

	public static String read(int idx) throws Exception{
        StringBuilder sb = new StringBuilder();
        int cur;
        while(true){
            cur = System.in.read();
            if(cur <= 32){
            	return sb.toString();
            }
            else{
            	int num = cur&15;
            	if(num == 8) eightCnt[idx]++;
                sb.append(cur&15);
            }
        }
    }
}