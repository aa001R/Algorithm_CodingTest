import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static String input;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++){
			input = br.readLine();
			if (fold(0, input.length() - 1)){
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}
		System.out.print(sb);
	}

	static boolean fold(int start, int end){
		if(start == end){
			return true;
		}
		int mid = (start + end) / 2;
		for(int i = start; i < mid; i++){
			if (input.charAt(i) == input.charAt(end-i)) {
				return false;
			}
		}
		return fold(start, mid - 1) && fold(mid + 1, end);
	}
}
