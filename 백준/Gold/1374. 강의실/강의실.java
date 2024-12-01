import java.io.*;
import java.util.*;

class Lecture implements Comparable<Lecture> {
    int num, startT, endT; // 강의 번호, 시작 시간, 종료 시간

    public Lecture(int num, int startT, int endT) {
        this.num = num;
        this.startT = startT;
        this.endT = endT;
    }

    // Comparable 강의 시작 시간을 기준으로 정렬
    // 시작 시간이 같으면 종료 시간을 기준으로 오름차순 정렬
    @Override
    public int compareTo(Lecture t) {
        if (this.startT == t.startT) return this.endT - t.endT;
        return this.startT - t.startT;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        int n = read(); // 강의 개수
        List<Lecture> lectures = new ArrayList<>(); // 강의 정보를 저장할 리스트
        
        // 강의 정보를 입력받아 Lecture 객체로 리스트에 추가
        for (int i = 0; i < n; i++) {
            int num = read();    // 강의 번호
            int startT = read(); // 강의 시작 시간
            int endT = read();   // 강의 종료 시간
            lectures.add(new Lecture(num, startT, endT));
        }
        
        // 강의를 시작 시간 순(-> 종료 시간 순)으로 정렬
        Collections.sort(lectures);

        // 우선순위 큐(PriorityQueue): 종료 시간이 빠른 순으로 정렬
        PriorityQueue<Integer> q = new PriorityQueue<>();
        
        int max = 1; // 필요한 최소 강의실 개수

        // 정렬된 강의를 순차적으로 처리
        for (int i = 0; i < n; i++) {
            // 종료 시간이 현재 강의 시작 시간보다 작은(끝난) 강의 제거
            while (!q.isEmpty() && q.peek() <= lectures.get(i).startT) {
                q.poll();
            }
            // 현재 강의의 종료 시간을 큐에 추가
            q.offer(lectures.get(i).endT);
            // 큐 크기(동시에 열리는 강의 수)가 최대 강의실 개수
            max = Math.max(max, q.size());
        }
        System.out.println(max);
    }

    static int read() throws IOException {
        int n = System.in.read() & 15, cur;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((cur = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (cur & 15);
        }
        return isNegative ? -n : n;
    }
}
