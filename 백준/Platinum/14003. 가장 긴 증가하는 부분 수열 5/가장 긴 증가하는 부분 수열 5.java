import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = read();
		int[] arr = new int[n];
		int[] lis = new int[n + 1];
		// 각각 수(arr[i])에 대해 가장 긴 수열에서의 위치를 저장
		int indexArr[] = new int[n + 1];
		for (int i = 0; i < n; i++) {
			arr[i] = read();
		}
		lis[0] = -1_000_000_001;

		int len = 0, idx = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] > lis[len]) {
				len += 1;
				lis[len] = arr[i];
				indexArr[i] = len;
			} else {
				idx = lowerBound(0, len, arr[i], lis);
				lis[idx] = arr[i];
				indexArr[i] = idx;
			}
		}
		bw.append(Integer.toString(len)).append("\n");
		// 역추적 경로를 저장할 stack
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		// 뒤에서 찾을 최대 인덱스
		int index = len;
		// 역으로 인덱스 추적
		for(int i = n; i >= 0; i--){
			// 찾길 원하는 인덱스와 같은 경우
			if(indexArr[i] == index){
				index--;
				stack.push(arr[i]);
			}
		}

		while (!stack.isEmpty()){
			bw.append(Integer.toString(stack.pop())).append(" ");
		}
		bw.flush();
	}

	static int lowerBound(int left, int right, int key, int[] lis) {
		int mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			if (lis[mid] < key) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

	public static int read() throws IOException {
		int n = System.in.read() & 15, cur;
		boolean isNegative = (n == 13);
		if (isNegative) {
			n = System.in.read() & 15;
		}
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? ~n + 1: n;
	}
}
