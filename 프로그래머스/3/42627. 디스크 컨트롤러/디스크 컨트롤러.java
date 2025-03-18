import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<int[]> que = new PriorityQueue<>(Comparator.comparingInt((int [] a) -> a[1])
			.thenComparingInt(a -> a[0])
			.thenComparingInt(a-> a[2]));
        
        Arrays.sort(jobs, Comparator.comparingInt((int [] a) -> a[0]).thenComparingInt(a -> a[1]));
        
        int jobCnt = jobs.length;
        int workTimeSum = jobs[0][1];
        int curTime = jobs[0][0] + jobs[0][1];
        
        // System.out.println(Arrays.toString(jobs[0])+" "+curTime+" "+workTimeSum);

        for (int i = 1; i < jobCnt; i++){
            while (curTime < jobs[i][0] && 
                !que.isEmpty() &&
                que.peek()[0] < jobs[i][0]){
                int [] job = que.poll();
                // System.out.println(Arrays.toString(job)+" "+curTime+" "+workTimeSum);
                curTime = Math.max(curTime, job[0])+job[1];
                workTimeSum += curTime - job[0];
            }
            que.add(new int[] {jobs[i][0], jobs[i][1], i});
        }
        
        while(!que.isEmpty()){
            int [] job = que.poll();
            // System.out.println(Arrays.toString(job)+" "+curTime+" "+workTimeSum);
            curTime = Math.max(curTime, job[0])+job[1];
            workTimeSum += curTime - job[0];
        }
            // System.out.println(curTime+" "+workTimeSum);
        
        answer = workTimeSum / jobCnt;
        return answer;
    }
}