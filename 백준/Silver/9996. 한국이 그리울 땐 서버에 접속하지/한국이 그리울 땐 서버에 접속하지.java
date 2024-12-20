import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int fileCnt = Integer.parseInt(br.readLine());
		String pattern = br.readLine();		//패턴
		String[] pArr = pattern.split("\\*");	//*을 기준으로 두개의 문자열로 나누기
		for (int i = 0; i < fileCnt; i++) {
			String str = br.readLine();
			if (pattern.length() - 1 > str.length()) {
				bw.append("NE").append("\n");
				continue;
			}
			String front = str.substring(0, pArr[0].length());
			String back = str.substring(str.length()-pArr[1].length());

			if (front.equals(pArr[0]) && back.equals(pArr[1])) {
				bw.append("DA").append("\n");
			} else {
				bw.append("NE").append("\n");
			}
		}
		bw.flush();
	}
}