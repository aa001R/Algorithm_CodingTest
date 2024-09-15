import java.util.*;
import java.io.*;

public class Main {
    static int N, M, L, K;
    static int[][] stars;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = read();
        M = read();
        L = read();
        K = read();
        stars = new int[K][2];
        for(int i =0 ; i < K ; i++){
            stars[i][0] = read();
            stars[i][1] = read();
        }
        int maxReturnStar = Integer.MIN_VALUE;
        for(int[] x: stars){
            for(int[] y: stars){
            	maxReturnStar = Math.max(maxReturnStar, returnStar(x[0], y[1]));
            }
        }
        System.out.println(K-maxReturnStar);
    }

    private static int returnStar(int x, int y) {
        int cnt = 0;
        for(int[] s:stars){
            if( x <= s[0] && s[0]<= x+L && y <= s[1] && s[1] <= y+L ) cnt++;
        }
        return cnt;
    }
    
    private static int read() throws Exception{
    	int n = 0, cur;
    	boolean isNumber = false;
    	while(true) {
    		cur = System.in.read();
    		if(cur <= 32) {
    			if(isNumber) return n;
    		}
    		isNumber = true;
    		n = (n << 3) + (n << 1) + (cur & 15);
    	}
    }
}