import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sx = -1, sy = -1;
        int litterCount = 0;
        int[][] litterIdx = new int[m][n];
        for (int[] row : litterIdx) {
            Arrays.fill(row, -1);
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    sx = r;
                    sy = c;
                } else if (ch == 'L') {
                    litterIdx[r][c] = litterCount++;
                }
            }
        }

        int targetMask = (1 << litterCount) - 1;
        if (targetMask == 0) {
            return 0;
        }

        // maxEnergy[r][c][mask] stores the maximum remaining energy seen for state (r, c, mask)
        int[][][] maxEnergy = new int[m][n][1 << litterCount];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                Arrays.fill(maxEnergy[r][c], -1);
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy, 0, energy, 0}); // {r, c, mask, curEnergy, moves}
        maxEnergy[sx][sy][0] = energy;

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int mask = curr[2];
            int e = curr[3];
            int moves = curr[4];

            if (mask == targetMask) {
                return moves;
            }

            if (e == 0) {
                continue;
            }

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    char cell = classroom[nr].charAt(nc);
                    if (cell == 'X') {
                        continue;
                    }

                    int nextMask = mask;
                    if (cell == 'L' && litterIdx[nr][nc] != -1) {
                        nextMask |= (1 << litterIdx[nr][nc]);
                    }

                    int nextEnergy = e - 1;
                    if (cell == 'R') {
                        nextEnergy = energy;
                    }

                    if (nextEnergy > maxEnergy[nr][nc][nextMask]) {
                        maxEnergy[nr][nc][nextMask] = nextEnergy;
                        queue.offer(new int[]{nr, nc, nextMask, nextEnergy, moves + 1});
                    }
                }
            }
        }

        return -1;
    }
}