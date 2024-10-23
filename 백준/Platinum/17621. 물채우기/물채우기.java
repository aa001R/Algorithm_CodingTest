import java.io.IOException;
import java.util.Arrays;

public class Main {
	public static class Column {
		int startY, endY;
		int[] waterH;
		public Column(int startY, int endY) {
			this.startY = startY;
			this.endY = endY;
			waterH = new int[2];
		}
	}

	static Column[] columns;
	static int N, M;
	public static void main(String[] args) throws IOException {
		N = read();
		M = read();
		columns = new Column[M];
		for (int i = 0; i < M; i++) {
			int startY = read();
			int endY = read();
			if (endY == 0) {
				// 비어있는 물에 대해서도 동일하게 계산하기 위해 치환
				startY = N + 1;
				endY = N + 1;
			}
			columns[i] = new Column(startY, endY);
		}
		// 바닥에 붙어있는 덩어리만 고려하여 물이 고이는 높이 (waterH[0]) 계산
		calcWaterH(0, M, 1, 0); // left -> right
		calcWaterH(M - 1, -1, -1, 0); // left <- right
		// 공중에 뜬 덩어리만 고려하여 물이 고이는 높이 (waterH[1]) 계산
		calcFloatingWaterH();
		// 물이 고이는 칸의 총 개수 계산
		System.out.println(calcTotalWater());
	}

	private static void calcWaterH(int start, int end, int dir, int target) {
		int min = N + 1;
		for (int cur = start; cur != end; cur += dir) {
			if (target != 0 || columns[cur].endY >= N) {
				// 바닥에 붙어 있는 덩어리만 고려시(target == 0) - 공중에 뜬 덩어리는 제외
				min = Math.min(min, columns[cur].startY);
			}
			columns[cur].waterH[target] = Math.max(min, columns[cur].waterH[target]);
		}
	}

	private static void calcFloatingWaterH() {
		for (int start = 0; start < M; ) {
			if (columns[start].endY >= N) {
				start++;
			} else {
				int end = start + 1;
				for (; end < M; end++) {
					if (isNotLink(end, end - 1)) {
						break;
					}
				}
				calcWaterH(start, end, 1, 1);
				calcWaterH(end - 1, start - 1, -1, 1);
				start = end;
			}
		}
	}

	private static boolean isNotLink(int a, int b) {
		return (columns[a].startY > columns[b].endY || columns[a].endY < columns[b].startY);
	}

	private static long calcTotalWater() {
		long sum = 0;
		for (int cur = 0; cur < M; cur++) {
			if(columns[cur].endY >= N){
				// 바닥에 붙어있는 (덩어리 기반) 물 높이
				sum += columns[cur].startY - columns[cur].waterH[0];
			} else {
				// 공중에 뜬 덩어리 내의 물 높이
				sum += columns[cur].startY - columns[cur].waterH[1];
				// 공중에 뜬 덩어리 아래의 물 높이
				sum += N + 1 - columns[cur].waterH[0];
				// 공중에 뜬 덩어리와 물이 아래 물에 포함될 경우 빼주기
				int underWaterH = Math.max(columns[cur].waterH[0], columns[cur].waterH[1]);
				if(underWaterH <= columns[cur].endY){
					sum -= (columns[cur].endY - underWaterH + 1);
				}
			}
		}
		return sum;
	}

	public static int read() throws IOException {
		int cur, n = System.in.read() & 15;
		boolean isNegative = (n == 13);
		if (isNegative) {
			n = System.in.read() & 15;
		}
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? ~n + 1 : n;
	}
}
