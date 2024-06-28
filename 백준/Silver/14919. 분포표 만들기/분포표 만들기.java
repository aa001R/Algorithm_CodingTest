import java.util.*;
import java.io.*;
/*
 * 컴퓨터가 숫자를 처리할 때에는 1.xxxxx(지수부) * 2^(가수부) 로 인식하기 때문에 2의 배수가 아니거나 소수인 값은 내가 저장한 값이 그대로 저장되지 않는 경우가 있다고 합니다. 
 * 이 상황에서 오차가 발생하기 때문에 그 오차를 해결하는 과정을 확인하기 위한 문제였다고 볼 수 있습니다. 
 * 예를 들어, double a = 0.1; 이러한 코드를 작성하였다 해도 a에는 0.1 이 아니라 0.1000000001 와 같은 값이 저장될 수 있다고 합니다.
 * 따라서 이 오차를 해결하기 위해서 아래의 코드에서 0.0000000001 값을 더했습니다.
 * 또한 각 구간이 T로 나눈 값이기 때문에 각 부등식에 T를 곱하면 계산이 수월해집니다.
 * 따라서 입력값에 0.0000000001 를 더한 후 T를 곱한 뒤 int형으로 타입캐스팅을 하면 정수부분만이 나옵니다. 
 * 즉, 배열의 인덱스 값이 나오게 되는 것이죠. 
 * 그래서 이 인덱스에 1씩 더해주면 그 구간에서의 숫자의 개수가 되게 됩니다.
 * */
class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException
	{
		int m = Integer.parseInt(br.readLine());
		int count [] = new int[m];
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			double num = Double.parseDouble(st.nextToken()) + 0.0000000001;
			count[(int)(num * m)]++;
		}
		for(int cnt : count) {
			bw.append(Integer.toString(cnt)).append(" ");
		}
		bw.flush();
	}
}