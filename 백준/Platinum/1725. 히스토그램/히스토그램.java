import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main {
public static int[] histogram;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N;
		N = Integer.parseInt(br.readLine());
		histogram = new int[N];
		for(int i = 0; i < N; i++) {
			histogram[i] = Integer.parseInt(br.readLine());
		}
		bw.append(Long.toString(getArea(N))).append('\n');
		bw.flush();
	}
	
	public static long getArea(int len) {
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		long maxArea = 0;
		for(int i = 0; i < len; i++) {
			// stack.peek(지금까지 중 가장 높은(새로운 높이보다 작아야하는) 높이)와 새로운 높이를 계속 비교함
			while((!stack.isEmpty()) 
					&& histogram[stack.peek()] >= histogram[i]) {
				int height = histogram[stack.pop()];	// 이전 체인의 높이
				long width = stack.isEmpty() ? i : i - 1 - stack.peek(); 
				maxArea = Math.max(maxArea, height * width);	// 최대 넓이 값 갱신
			}
			stack.push(i);
		}
		// 위 과정이 끝나고 Stack에 남아있는 체인들이 존재할 수 있으므로 나머지도 위와같은 과정을 거친다.
        while(!stack.isEmpty()) {
            int height = histogram[stack.pop()];
            long width = stack.isEmpty() ? len: len - 1 - stack.peek();
            maxArea = Math.max(maxArea, width * height);
        }
        return maxArea;
	}
}