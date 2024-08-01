import java.io.*;
 
import java.util.*;
 
public class Main {
	static int n, m, sidewalkBlock[][], jumpPower;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = read();
        m = read();
        sidewalkBlock = new int[n][m];
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j < m; j++) {
        		sidewalkBlock[i][j] = read();
        	}
        }
        jumpPower = read();
        boolean isAlive = false;
        if(sidewalkBlock[0][0] == sidewalkBlock[n-1][m-1] && bfs(0, 0)) {
        	bw.append("ALIVE\n");
        }else {
        	bw.append("DEAD\n");
        }
        bw.flush();
    }
	private static boolean bfs(int i, int j) {
		ArrayDeque<int []> que = new ArrayDeque<>();
		boolean visited[][] = new boolean[n][m];
		visited[i][j] = true;
		que.offer(new int[] {i, j, sidewalkBlock[i][j]});
		while(!que.isEmpty()){
			int [] cur = que.poll();
			for (int nr = 0; nr < n; nr++) {
		        for (int nc = 0; nc < m; nc++) {
		        	if(cur[2] != sidewalkBlock[nr][nc]) continue;
		        	if(visited[nr][nc]) continue;
					int dis = Math.abs(cur[0] - nr) + Math.abs(cur[1] - nc);
					if(dis > jumpPower) {
						if(cur[1] < nc) break;
						continue;
					}
					if(nr == n-1 && nc == m-1) return true;
					visited[nr][nc] = true;
					que.offer(new int[] {nr, nc, sidewalkBlock[nr][nc]});
				}
		        if (Math.abs(cur[0] - nr) > jumpPower && cur[0] < nr) {
		            break;
		        }
			}
		}
		return false;
	}
	private static boolean isOut(int nr, int nc) {
		return (nr < 0 || nr >= n || nc < 0 || nc >= m);
	}
	private static int read() throws Exception {
		int n = 0;
		int cur;
		boolean isNumber = false;
		while(true) {
			cur = System.in.read();
			if(cur <= 32) {
				if(isNumber) return n;
			}else {
				isNumber = true;
				n = (n<<3)+(n<<1)+(cur&15);
			}
		}
	}
}