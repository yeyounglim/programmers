import java.util.*;

public class Main {
    static int N, M;
    static int[][] lab;
    static List<int[]> emptySpaces = new ArrayList<>();
    static List<int[]> viruses = new ArrayList<>();
    static int maxSafeArea = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();

        lab = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                lab[i][j] = scanner.nextInt();
                if (lab[i][j] == 0) emptySpaces.add(new int[]{i, j});
                else if (lab[i][j] == 2) viruses.add(new int[]{i, j});
            }
        }

        combination(new int[3], 0, 0);
        System.out.println(maxSafeArea);
    }

    public static void combination(int[] current, int wall, int start) {
        if (wall == 3) {
            int[][] tempLab = new int[N][M];
            for (int i = 0; i < N; i++) {
                tempLab[i] = lab[i].clone();
            }

            for (int i : current) {
                int[] pos = emptySpaces.get(i);
                tempLab[pos[0]][pos[1]] = 1;
            }

            maxSafeArea = Math.max(maxSafeArea, getSafeArea(tempLab));
            return;
        }
        for (int i = start; i < emptySpaces.size(); i++) {
            current[wall] = i;
            combination(current, wall + 1, i + 1);
        }
    }

    private static int getSafeArea(int[][] tempLab) {
        Queue<int[]> queue = new LinkedList<>(viruses);

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int i = 0; i < 4; i++) {
                int row = pos[0] + dr[i];
                int column = pos[1] + dc[i];

                if (row >= 0 && column >= 0 && row < N && column < M && tempLab[row][column] == 0) {
                    tempLab[row][column] = 2;
                    queue.add(new int[]{row, column});
                }
            }
        }

        return (int) Arrays.stream(tempLab)
                .flatMapToInt(Arrays::stream)
                .filter(value -> value == 0)
                .count();
    }
}
