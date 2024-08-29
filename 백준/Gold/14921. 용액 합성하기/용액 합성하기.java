import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static long min = Long.MAX_VALUE;
    static long[] liquid;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        liquid = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            liquid[i] = Long.parseLong(st.nextToken()); // 오름차순으로 입력됨
        }
        twoPointer(n);
        bw.append(Long.toString(min));
        bw.flush();
    }

    public static void twoPointer(int n){
        int left =0, right =n-1;
        int minL =0, minR = 0;
        while(left<right) {
            long sum = liquid[left]+liquid[right];
            if(Math.abs(min) > Math.abs(sum)) {
                min = sum;
                minL = left; minR = right;
            }
            if(sum == 0) return;
            else if(sum>0) right--;
            else left++;
        }
    }
}