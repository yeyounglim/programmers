class Solution {
    private static int answer = 0;
    private static boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        backtrack(k, dungeons, 0);
        return answer;
    }

    private void backtrack(int k, int[][] dungeons, int count) {
        if (answer < count) {
            answer = count;
        }

        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) continue;
            if (k >= dungeons[i][0]) {
                visited[i] = true;
                backtrack(k- dungeons[i][1], dungeons, count + 1);
                visited[i] = false;
            }
        }
    }
}
