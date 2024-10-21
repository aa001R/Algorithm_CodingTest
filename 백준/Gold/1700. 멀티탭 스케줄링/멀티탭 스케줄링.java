import java.io.*;
import java.util.*;

public class Main {
	static int N, K, useOrder[], change = 0;
	static boolean [] used;
    public static void main(String[] args) throws Exception {
    	N = read();
    	K = read();
    	useOrder = new int[K];
    	used = new boolean[K+1];
    	for(int k = 0; k < K; k++) {
    		useOrder[k] = read();
    	}
    	int cur = 0;
    	for(int n = 0; n < N && cur < K; cur++) {
    		if(used[useOrder[cur]]) continue;
    		used[useOrder[cur]] = true;
    		n++;
    	}    	
    	for(; cur < K; cur++) {
    		if(used[useOrder[cur]]) { continue; }
    		
    		ArrayList<Integer> useList = new ArrayList<>();
            for (int next = cur; next < K; next++) {
                if(!used[useOrder[next]] || useList.contains(useOrder[next])) continue;
                useList.add(useOrder[next]);
            }
            if (useList.size() < N) {
                for (int removal = 0; removal <= K; removal++) {
                	if(!used[removal] || useList.contains(removal)) continue;
                	used[removal] = false;
                    break;
                }
            } else {
                int remove = useList.get(useList.size() - 1);
                used[remove] = false;
            }
            used[useOrder[cur]] = true;
            change++;
    	}
    	System.out.println(change);
    }

	private static int read() throws Exception {
		int n = 0, cur;
		boolean isNumber = false;
		while(true) {
			cur = System.in.read();
			if(cur <= 32) { if(isNumber) return n; }
			else {
				isNumber = true;
				n = (n<<3)+(n<<1)+(cur&15);
			}
		}
	}
}