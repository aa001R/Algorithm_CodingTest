import java.util.*;
import java.io.*;

class Main {
	static class Point{
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int BESSIE = 8, DAISY = 4;
	static Point john;
	static int [][] delta = {{1, 0},{0, 1},{-1, 0},{0, -1},{-1, -1},{1, 1},{1, -1},{-1, 1}};
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Point cowPoint[] = new Point[2];
		for(int i = 0; i < 2; i++) {
			cowPoint[i] = new Point(read(), read());
		}
		john = new Point(read(), read());
		int bessieArriveTime = bfs(BESSIE, cowPoint[0]);
		int daisyArriveTime = bfs(DAISY, cowPoint[1]);
		if(bessieArriveTime < daisyArriveTime) {
			bw.append("bessie");
		}else if(bessieArriveTime == daisyArriveTime) {
			bw.append("tie");
		}else {
			bw.append("daisy");
		}
		bw.flush();
	}
	private static int bfs(int cowMove, Point point) {
		boolean [][] visited = new boolean[1001][1001];
		ArrayDeque <Point> q = new ArrayDeque<>();
		visited[point.r][point.c] = true;
		q.offer(point);
		int time = 0;
		Meet : while(!q.isEmpty()) {
			time++;
			for(int size = q.size(); size > 0; size--) {
				Point cur = q.poll();
				for(int d = 0; d < cowMove; d++) {
					int nr = cur.r + delta[d][0];
					int nc = cur.c + delta[d][1];
					if(isOut(nr,nc)) continue;
					if(visited[nr][nc]) continue;
					if(nr == john.r && nc == john.c) break Meet;
					visited[nr][nc] = true;
					q.offer(new Point(nr, nc));
				}
			}
		}
		return time;
	}
	private static boolean isOut(int nr, int nc) {
		return nr < 0 || nr >= 1001 || nc < 0 || nc >= 1001;
	}
	static public int read() throws Exception {
		int n = 0, cur;
		boolean isNumber = false;
		while(true) {
			cur = System.in.read();
			if(cur <= 32) {
				if (isNumber) return n;
			}else {
				isNumber = true;
				n = (n<<3)+(n<<1)+(cur&15);
			}
		}
	}
}