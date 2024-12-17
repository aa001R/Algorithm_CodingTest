import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int A = read();
		int B = read();
		int C = read();
		List<ParkingTime> parkingTimes = new ArrayList<>();
		int minT = 101, maxT = 0;
		for (int i = 0; i < 3; i++) {
			int startT = read();
			int endT = read();
			parkingTimes.add(new ParkingTime(startT, endT));
			if (startT < minT) { minT = startT; }
			if (endT > maxT) { maxT = endT; }
		}

		int [] time = new int [101];
		for (ParkingTime parkingTime : parkingTimes) {
			for (int j = parkingTime.startT; j < parkingTime.endT; j++) {
				time[j]++;
			}
		}

		int cost = 0;
		for (int i = minT; i <= maxT; i++) {
			switch (time[i]){
				case 1 : cost += A; break;
				case 2 : cost += (B*2); break;
				case 3 : cost += (C*3); break;
			}
		}
		System.out.println(cost);
	}

	static class  ParkingTime {
		int startT, endT;
		public ParkingTime(int startT, int endT) {
			this.startT = startT;
			this.endT = endT;
		}
	}

	public static int read() throws IOException {
		int n = System.in.read() & 15, cur;
		boolean isNegative = n == 13;
		if (isNegative) { n = System.in.read() & 15; }
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? -n : n;
	}
}
