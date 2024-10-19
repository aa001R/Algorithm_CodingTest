import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int H = read();
		int W = read();
		int[] height = new int[W];
		int rain = 0;
		for(int i=0;i<W;i++)
			height[i] = read();

		for(int i=1;i<W-1;i++){
			int left = 0, right = 0;	//좌측, 우측 최대 높이 블럭 변수

			for(int j=0;j<i;j++)
				left = Math.max(left, height[j]);
			for(int j=i+1;j<W;j++)
				right = Math.max(right, height[j]);

			if(height[i] < left && height[i] < right){
				rain += Math.min(left, right) - height[i];
			}
		}
		bw.write(rain + "");	//고인 모든 빗물 양 BufferedWriter 저장
		bw.flush();		//결과 출력
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if (isNegative) {
			n = System.in.read() & 15;
		}
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? ~n + 1 : n;
	}
}
