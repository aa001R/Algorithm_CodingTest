import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Integer> A = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			A.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		List<Integer> B = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			B.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(A);
		Collections.sort(B, Collections.reverseOrder());
		int S = 0;
		for(int i = 0; i < n; i++) {
			S += A.get(i) * B.get(i);
		}
		System.out.println(S);
	}
}