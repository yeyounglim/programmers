import java.util.*;

class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < answer.length; i++) {
            if (placeDistance(places[i]))
                answer[i] = 1;
            else answer[i] = 0;
        }
        return answer;
    }

    private boolean placeDistance(String[] place) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i].charAt(j) == 'P') {
                    if (!bfs(place, i, j))
                        return false;
                }
            }
        }
        return true;
    }

    private boolean bfs(String[] place, int row, int column) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];

        queue.offer(new int[]{row, column});
        visited[row][column] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int distance = Math.abs(row - r) + Math.abs(column - c);

            if (distance > 2) continue;
            if (distance > 0 && place[r].charAt(c) == 'P') return false;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nc >= 0 && nr < 5 && nc < 5 && !visited[nr][nc] && place[nr].charAt(nc) != 'X') {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return true;
    }
}