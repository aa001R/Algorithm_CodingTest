import java.util.*;
import java.io.*;

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException
	{
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++)
		{
			String[] temp = br.readLine().split(" ");
			
			int a = Integer.parseInt(temp[0]);
			int b = Integer.parseInt(temp[1]);
			
			int result = a % 10;
			for (int j = 2; j <= b; j++)
			{
				result = (result * a) % 10;
			}
			if(result == 0) result=10;
			bw.append(Integer.toString(result)); bw.newLine();
		}
		bw.flush();
	}
}