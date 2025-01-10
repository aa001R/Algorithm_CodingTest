import java.io.*;
import java.util.*;

public class Main {
	static int K, size;
	static int [] num;
	static ArrayList<Integer> [] tree;
	public static void main(String[] args) throws Exception{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		K = read();
		size = (int) (Math.pow(2, K) - 1);
		tree = new ArrayList[K+1];
		num = new int[size];
		for(int i=1; i<=K; i++)
			tree[i] = new ArrayList<>();
		for(int i=0; i<size; i++)
			num[i] = read();
		orderBuilding(1, 0, size);
		for(int i=1; i<=K; i++){
			for(int j=0;j<tree[i].size();j++)
				bw.append(Integer.toString(tree[i].get(j))).append(" ");
			bw.newLine();
		}
		bw.flush();
	}

	static void orderBuilding(int depth, int start, int end){
		int mid = (start + end)/2;	// parent
		tree[depth].add(num[mid]);
		if(depth == K) return;
		orderBuilding(depth+1, start, mid-1);	//Left
		orderBuilding(depth+1, mid+1, end);	//Right
	}

	static int read() throws Exception{
		int n = System.in.read() & 15, cur;
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return n;
	}
}
