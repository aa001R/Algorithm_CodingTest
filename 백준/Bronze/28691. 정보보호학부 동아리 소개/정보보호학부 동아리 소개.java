import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Map<String, String> club = new HashMap<String, String>();
		club.put("M", "MatKor"); club.put("W", "WiCys"); club.put("C", "CyKor"); club.put("A", "AlKor"); club.put("$", "$clear");
		bw.write(club.get(br.readLine()));
		bw.flush();
	}
}
