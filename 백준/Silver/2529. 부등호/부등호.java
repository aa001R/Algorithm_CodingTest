import java.util.*;
import java.io.*;

public class Main {
	static int K;
	static boolean[] visited;
	static String[] arr;
	static List<String> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		K = Integer.parseInt(br.readLine());
		arr = br.readLine().split(" ");
		visited = new boolean[10];
		dfs("",0);
		bw.append(list.get(list.size() - 1)).append("\n");
		bw.append(list.get(0)).append("\n");
		bw.flush();
	}

	static void dfs(String num, int idx) {
		if(idx == K + 1) {
			list.add(num);
			return;
		}

		for(int j = 0 ; j < 10; j++) {
			if(visited[j])  continue;
			if(idx == 0 || ckeck(Character.getNumericValue(num.charAt(idx - 1)), j , arr[idx-1])) {
				visited[j] = true;
				dfs(num+j, idx+1);
				visited[j] = false;
			}
		}
	}

	static boolean ckeck(int a, int b, String c) {
		if (c.equals(">") && a > b) {
			return true;
		} else if (c.equals("<") && a < b){
			return true;
		}
		return false;
	}
}
