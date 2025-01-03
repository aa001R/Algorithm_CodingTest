import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken());
		int count = Integer.parseInt(st.nextToken()); 
		
		int temp = 0;
		int busket[] = new int[n];
		for(int i=0;i<busket.length;i++) {
			busket[i] = i+1;
		}
		
		for(int i=0;i<count;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			while(a<b) {
				temp = busket[a];
				busket[a] = busket[b];
				busket[b] = temp;
				a++; b--;
			}
		}
		for(int i=0;i<busket.length;i++) {
			System.out.print(busket[i]+" ");
		}
	}
}