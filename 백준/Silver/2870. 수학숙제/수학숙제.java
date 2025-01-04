import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<BigInteger> numbers = new ArrayList<>(); // BigInteger 사용
        int n = Integer.parseInt(br.readLine());
        Pattern pattern = Pattern.compile("\\d+");

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                numbers.add(new BigInteger(matcher.group()));
            }
        }
        Collections.sort(numbers);
        for (BigInteger number : numbers) {
            bw.append(number.toString()).append("\n");
        }
        bw.flush();
    }
}
