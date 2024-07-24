import java.io.*;
 
import java.util.*;
 
public class Main {
	static int MOD = 1_000_000_007;
	static int roShamBo = 0;
	static char lighterFirst;
	static String smallant;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		lighterFirst = br.readLine().charAt(0);
		smallant = br.readLine();
		backTracking(lighterFirst, 0, false, false);
		bw.write(Integer.toString(roShamBo));
		bw.flush();
	}
	private static void backTracking(char curLig, int cur, boolean winLig, boolean doGame) {
		if(cur == smallant.length()) {
			if(!doGame) return;
			roShamBo = (roShamBo+1) % MOD;
			return;
		}
		backTracking(curLig, cur+1, winLig, doGame);
		if(winLig && (curLig == smallant.charAt(cur))) { return; }
		boolean curWin = false;
		if(curLig == 'R' && smallant.charAt(cur) == 'S') curWin = true;
		else if(curLig == 'P' && smallant.charAt(cur) == 'R') curWin = true;
		else if(curLig == 'S' && smallant.charAt(cur) == 'P') curWin = true;
		backTracking(smallant.charAt(cur), cur+1, curWin, true);
	}
}