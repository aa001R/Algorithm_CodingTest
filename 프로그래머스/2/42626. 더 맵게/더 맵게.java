import java.util.*;


class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> heap = new PriorityQueue();
        int foodCnt = scoville.length;
        for(int i = 0; i < foodCnt; i++){
            heap.add(scoville[i]);
        }
        while(heap.peek() < K && heap.size() > 1){
            int a = heap.poll();
            int b = heap.poll() * 2;
            heap.add(a+b);
            answer++;
        }
        if (heap.peek() < K) answer = -1;
        return answer;
    }
}