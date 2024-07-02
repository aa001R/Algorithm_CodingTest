import java.util.*;
import java.io.*;

class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int divisor = gcd(x, y);
		if( divisor == 1) { // 1 이외의 공약수 없음
			bw.append(Integer.toString(x+y-1));
		}else { // 공약수 존재
			bw.append(Integer.toString(x+y-divisor));
		}
		bw.flush();
	}	
	
	public static int gcd(int a, int b)
	{
		return b != 0 ? gcd(b, a%b) : a;
	}
}