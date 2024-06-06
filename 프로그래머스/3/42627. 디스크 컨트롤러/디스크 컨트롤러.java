import java.util.*;

public class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        int complete = 0;
        int index = 0;
        int currentTime = 0;
        int totalTime = 0;

        while (complete < jobs.length) {
            while (index < jobs.length && jobs[index][0] <= currentTime) {
                pq.add(jobs[index]);
                index++;
            }
            if (pq.isEmpty()) {
                currentTime = jobs[index][0];
            } else {
                int[] job = pq.poll();
                currentTime += job[1];
                totalTime += currentTime - job[0];
                complete++;
            }
        }
        return totalTime / jobs.length;
    }
}