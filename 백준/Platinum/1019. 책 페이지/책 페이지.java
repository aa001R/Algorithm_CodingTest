import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
	
	static long[] cnt;
	static long start, end, digit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		digit = 1;
		start = 1;
		end = Long.parseLong(br.readLine());
		
		cnt = new long[10];
		
		while(start <= end) {
			// 시작 페이지의 마지막 자리가 0이 될 때 까지 ++ 
			while(start % 10 != 0 && start <= end) {
				counting(start, digit);
				start++;
			}
			
			// 마지막 페이지의 마지막 자리가 9가 될 때 까지 -- 
			while(end % 10 != 9 && start <= end) {
				counting(end, digit);
				end--;
			}
			
			if(start > end) break;
			
			// 마지막 자릿수를 제거한다. 
			start /= 10;
			end /= 10;
			
			// start ~ end 사이의 0 ~ 9 갯수를 모두 센다.
			// 현재 자릿수 만큼 곱해줘야한다. 
			// 어떤 숫자 A의 일의 자리가 0이고 B의 일의 자리가 9일 때 
			// A에서 B까지 0 ~ 9의 숫자가 등장하는 횟수는 (B/10 - A/10 + 1) * 원래 숫자에서의 자릿수다.
			for(int i = 0 ; i < 10 ; ++i) {
				cnt[i] += (end - start + 1) * digit;
			}
			
			// 자릿수를 높인다. 
			digit *= 10;
		}
		
		for(long i : cnt) {
			bw.append(Long.toString(i)).append(" ");
		}
		bw.flush();
	}

	private static void counting(long num, long digit) {
		while(num > 0) {
			cnt[(int)num % 10] += digit;
			num /= 10;
		}
	}
}