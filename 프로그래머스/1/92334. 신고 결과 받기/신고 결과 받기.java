import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> map = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        for (String id : id_list) {
            map.put(id, new HashSet<>());
            count.put(id, 0);
        }

        for (String r : report) {
            String[] separate = r.split(" ");
            String reporter = separate[0]; // 신고자
            String reported = separate[1]; // 신고 당한 사람

            if (map.get(reporter).add(reported))
                count.put(reported, count.get(reported) + 1);
        }

        Set<String> banned = new HashSet<>();
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            if (entry.getValue() >= k)
                banned.add(entry.getKey());
        }

        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            for (String reported : map.get(id_list[i])) {
                if (banned.contains(reported))
                    answer[i]++;
            }
        }
        return answer;
    }
}