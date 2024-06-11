import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
	

	static HashMap<Integer, ArrayList<Integer>> dp;

	static ArrayList<Integer> fibonacci(int n) {
		if (n == 0) {
			return dp.get(0);
		} else if (n == 1) {
			return dp.get(1);
		} else if( dp.containsKey(n)){
			return dp.get(n);
		} else {
			ArrayList<Integer> n_1 = fibonacci(n-1);
			ArrayList<Integer> n_2 = fibonacci(n-2);
			dp.put(n, new ArrayList<>(Arrays.asList(n_1.get(0)+n_2.get(0), n_1.get(1)+n_2.get(1))));
			return dp.get(n);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		dp = new HashMap<>();
		dp.put(0, new ArrayList<>(Arrays.asList(1, 0)));
		dp.put(1, new ArrayList<>(Arrays.asList(0, 1)));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			int input_fibo = Integer.parseInt(br.readLine());
			fibonacci(input_fibo);
			sb.append(dp.get(input_fibo).get(0)).append(" ").append(dp.get(input_fibo).get(1)).append("\n");
		}
		System.out.print(sb);
	}
}
