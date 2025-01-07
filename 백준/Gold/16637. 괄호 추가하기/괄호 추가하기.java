import java.io.*;

public class Main {
	static int [] operand;
	static char [] operator;
	static int maxCal = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		operand = new int[N/2+1];
		operator = new char[N/2];
		int opd = 0; int opr = 0;
		for(int i = 0; i < N; i++) {
			if(i % 2 == 0) {
				operand[opd++] = input.charAt(i)-'0';
			}
			else operator[opr++] = input.charAt(i);
		}
		calBFS(operand[0], 0);
		System.out.println(maxCal);
	}
	static int cal(int num1, char op, int num2) {
		switch (op) {
			case '+': return num1+num2;
			case '*': return num1*num2;
			case '-': return num1-num2;
		}
		return 0;
	}

	static void calBFS(int pre, int idx) {
		if(idx >= operator.length) {
			maxCal = Math.max(maxCal, pre);
			return;
		}
		calBFS(cal(pre, operator[idx], operand[idx+1]), idx+1);
		if(idx+1 < operator.length)
			calBFS(cal(pre, operator[idx], cal(operand[idx+1], operator[idx+1], operand[idx+2])), idx+2);
	}
}