import java.util.*;

public class Main {
    static int N, M;
    static int[][] lab;
    static int emptySpaces = 0;
    static List<int[]> viruses = new ArrayList<>();
    static int minTime = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();

        lab = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                lab[i][j] = scanner.nextInt();
                if (lab[i][j] == 0) emptySpaces++;
                else if (lab[i][j] == 2) viruses.add(new int[]{i, j});
            }
        }

        if (emptySpaces == 0) {
            System.out.println(0);
            return;
        }
        combination(new int[M], 0, 0);
        System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
    }

    public static void combination(int[] current, int activeVirus, int start) {
        if (activeVirus == M) {
            int[][] tempLab = new int[N][N];
            for (int i = 0; i < N; i++) {
                tempLab[i] = lab[i].clone();
            }

            Queue<int[]> queue = new LinkedList<>();

            for (int i : current) {
                int[] pos = viruses.get(i);
                queue.add(pos);
                tempLab[pos[0]][pos[1]] = 3;
            }
            bfs(queue, tempLab);
            return;
        }

        for (int i = start; i < viruses.size(); i++) {
            current[activeVirus] = i;
            combination(current, activeVirus + 1, i + 1);
        }
    }

    private static void bfs(Queue<int[]> queue, int[][] tempLab) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int time = 0;
        int emptyCount = emptySpaces;

        boolean[][] visited = new boolean[N][N];

        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;

            for (int j = 0; j < size; j++) {
                int[] pos = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int row = pos[0] + dr[i];
                    int column = pos[1] + dc[i];

                    if (row >= 0 && column >= 0 && row < N && column < N && !visited[row][column]) {
                        if (tempLab[row][column] == 0) {
                            queue.add(new int[]{row, column});
                            visited[row][column] = true;
                            emptyCount--;

                            if (emptyCount == 0) {
                                minTime = Math.min(minTime, time);
                                return;
                            }
                        } else if (tempLab[row][column] == 2) {
                            queue.add(new int[]{row, column});
                            visited[row][column] = true;
                        }
                    }
                }
            }
        }
    }
}
