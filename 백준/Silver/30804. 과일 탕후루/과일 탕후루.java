import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int [] fruits, tanghuru;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		fruits = new int[N];
		tanghuru = new int[10];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(twoPointer(0, 0, 0, 0, 0));
	}
	private static int twoPointer(int left, int right, int cnt, int kind, int max) {
        if (right >= N) {
            return max;
        }
        if (tanghuru[fruits[right]] == 0) {
            kind++;
        }
        cnt++;
        tanghuru[fruits[right]]++;
        if (kind > 2) {
            if (--tanghuru[fruits[left]] == 0) {
                kind--;
            }
            cnt--;
            left++;
        }
        max = Math.max(max, cnt);
        return twoPointer(left, right + 1, cnt, kind, max);
    }
}
