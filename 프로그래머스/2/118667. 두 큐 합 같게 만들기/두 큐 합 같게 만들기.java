import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Deque<Integer> deque1 = new ArrayDeque<>();
        Deque<Integer> deque2 = new ArrayDeque<>();

        Arrays.stream(queue1).forEach(deque1::add);
        Arrays.stream(queue2).forEach(deque2::add);

        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();

        if ((sum1 + sum2) % 2 != 0) return -1;

        int count = 0;
        int maxCount = (queue1.length + queue2.length) * 2;

        while (sum1 != sum2 && count <= maxCount) {
            if (sum1 > sum2) {
                int poll = deque1.poll();
                deque2.add(poll);
                sum1 -= poll;
                sum2 += poll;
            } else {
                int poll = deque2.poll();
                deque1.add(poll);
                sum2 -= poll;
                sum1 += poll;
            }
            count++;
        }
        return sum1 == sum2 ? count : -1;
    }
}