import java.io.*;
import java.util.*;

class Chicken{
	int r, c;
	public Chicken(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static int homeCnt, chickenCnt;
	static int N, M;
	static int minChickenDistance = Integer.MAX_VALUE;
	static int [] minDistances;
	static int [][] distances;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = read();
		M = read();
		LinkedList<int []> chickenList = new LinkedList();
		LinkedList<int []> homeList = new LinkedList();
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				int curr = read();
				if(curr == 1) homeList.add(new int[] {r, c});
				if(curr == 2) chickenList.add(new int[] {r, c});
			}
		}
		minDistances = new int[homeList.size()];
		distances = new int[homeList.size()][chickenList.size()];
		for(int i = 0; i < homeList.size(); i++) {
			minDistances[i] = Integer.MAX_VALUE;
		}
		for(int r = 0; r < homeList.size(); r++) {
			for(int c = 0; c < chickenList.size(); c++) {
				distances[r][c] = Math.abs(homeList.get(r)[0] - chickenList.get(c)[0])
								  + Math.abs(homeList.get(r)[1] - chickenList.get(c)[1]);
			}
		}
		comb(0, 0);
		bw.write(Integer.toString(minChickenDistance));
		bw.flush();
	}
	static void comb(int toSelect, int startIdx) {
		if(toSelect == M) {
			int sum = 0;
			for(int d : minDistances) {
				sum += d;
				if(sum > minChickenDistance) return;
			}
			minChickenDistance =  Math.min(minChickenDistance, sum);
			return;
		}

		int tmpDistances[] = new int[ minDistances.length];
		for(int chicken = startIdx; chicken < distances[0].length; chicken++) {
			// 현재 chicken 집 선택했을 경우 집과의 거리 갱신
			for(int home = 0; home < minDistances.length; home++) {
				tmpDistances[home] = minDistances[home];
				minDistances[home] = Math.min(minDistances[home], distances[home][chicken]);
			}
			comb(toSelect+1, chicken+1);
			for(int home = 0; home < minDistances.length; home++) {
				minDistances[home] = tmpDistances[home];
			}
		}
	}

	static int read() throws Exception {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) { n = System.in.read() & 15; }
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? -n : n;
	}
}
