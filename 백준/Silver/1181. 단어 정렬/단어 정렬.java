
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		HashSet<String> s = new HashSet<String>();
		for(int i = 0; i < n; i++) {
			s.add(br.readLine());
		}
		String [] arr = new String[s.size()];
		int next = 0;
		Iterator<String> iter = s.iterator();
		while(iter.hasNext()) {
			arr[next++] = iter.next();
		}
		Arrays.sort(arr, new Comparator<String>() {
			public int compare(String s1, String s2) {
				// 단어 길이가 같을 경우 
				if (s1.length() == s2.length()) {
					return s1.compareTo(s2);
				} 
				// 그 외의 경우 
				else {
					return s1.length() - s2.length();
				}
			}
		});
		for(String str:arr) {
			System.out.println(str);
		}
	}
}
