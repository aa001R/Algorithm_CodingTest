import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int goalCnt = Integer.parseInt(br.readLine());
		int [] teamGoalCnt = new int [3];
		int [] winTime = new int [3];
		int lastWinTime = 0;

		for (int i = 0; i < goalCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int team = Integer.parseInt(st.nextToken());
			int goalTime = strToTime(st.nextToken().split(":"));
			if ( teamGoalCnt[1] > teamGoalCnt[2]) {
				winTime[1] += goalTime - lastWinTime;
			} else if (teamGoalCnt[1] < teamGoalCnt[2]) {
				winTime[2] += goalTime - lastWinTime;
			}
			teamGoalCnt[team]++;
			lastWinTime = goalTime;
		}
		if (teamGoalCnt[1] > teamGoalCnt[2]) {
			winTime[1] += 48 * 60 - lastWinTime;
		} else if (teamGoalCnt[1] < teamGoalCnt[2]) {
			winTime[2] += 48 * 60 - lastWinTime;
		}
		bw.append(timeToStr(winTime[1]))
			.append(timeToStr(winTime[2]));
		bw.flush();
	}

	static int strToTime(String [] str) {
		return Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
	}

	static String timeToStr(int time) {
		return String.format("%02d:%02d\n", time / 60, time % 60);
	}
}
