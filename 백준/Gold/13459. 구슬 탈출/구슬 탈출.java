import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        board = new char[N][M];

        int rr = 0, rc = 0, br = 0, bc = 0;
        for (int i = 0; i < N; i++) {
            String line = scanner.next();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    rr = i;
                    rc = j;
                    board[i][j] = '.';
                } else if (board[i][j] == 'B') {
                    br = i;
                    bc = j;
                    board[i][j] = '.';
                }
            }
        }
        System.out.println(bfs(new Position(rr, rc, br, bc, 0)));
    }

    private static int bfs(Position start) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        boolean[][][][] visited = new boolean[N][M][N][M];

        Queue<Position> queue = new LinkedList<>();
        queue.add(start);

        visited[start.redRow][start.redCol][start.blueRow][start.blueCol] = true;
        
        while (!queue.isEmpty()) {
            Position current = queue.poll();
            if (current.depth >= 10) return 0;

            for (int i = 0; i < 4; i++) {
                int[] movedRed = move(current.redRow, current.redCol, dr[i], dc[i]);
                int[] movedBlue = move(current.blueRow, current.blueCol, dr[i], dc[i]);

                int nrr = movedRed[0], nrc = movedRed[1];
                int nbr = movedBlue[0], nbc = movedBlue[1];

                if (board[nbr][nbc] == 'O') continue;
                if (board[nrr][nrc] == 'O') return 1;

                if (nrr == nbr && nrc == nbc) {
                    if (movedRed[2] > movedBlue[2]) {
                        nrr -= dr[i];
                        nrc -= dc[i];
                    } else {
                        nbr -= dr[i];
                        nbc -= dc[i];
                    }
                }

                if (!visited[nrr][nrc][nbr][nbc]) {
                    visited[nrr][nrc][nbr][nbc] = true;
                    queue.add(new Position(nrr, nrc, nbr, nbc, current.depth + 1));
                }
            }
        }
        return 0;
    }

    private static int[] move(int row, int column, int dr, int dc) {
        int distance = 0;
        while (board[row + dr][column + dc] != '#' && board[row][column] != 0) {
            row += dr;
            column += dc;
            distance++;
            if (board[row][column] == 'O') break;
        }
        return new int[]{row, column, distance};
    }
}

class Position {
    int redRow, redCol, blueRow, blueCol, depth;

    public Position(int redRow, int redCol, int blueRow, int blueCol, int depth) {
        this.redRow = redRow;
        this.redCol = redCol;
        this.blueRow = blueRow;
        this.blueCol = blueCol;
        this.depth = depth;
    }
}