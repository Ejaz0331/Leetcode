import java.util.*;

class Solution {
    public int[][] colorGrid(int n, int m, int[][] sources) {
        int[][] res = new int[n][m];
        Queue<int[]> queue = new LinkedList<>();

        for (int[] s : sources) {
            int r = s[0], c = s[1], color = s[2];
            res[r][c] = color;
            queue.offer(new int[]{r, c, color});
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int size = queue.size();
            Map<Integer, Integer> nextLevel = new HashMap<>();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0], c = curr[1], color = curr[2];

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (nr >= 0 && nr < n && nc >= 0 && nc < m && res[nr][nc] == 0) {
                        int pos = nr * m + nc;
                        nextLevel.put(pos, Math.max(nextLevel.getOrDefault(pos, 0), color));
                    }
                }
            }

            for (Map.Entry<Integer, Integer> entry : nextLevel.entrySet()) {
                int pos = entry.getKey();
                int color = entry.getValue();
                int r = pos / m;
                int c = pos % m;

                if (res[r][c] == 0) {
                    res[r][c] = color;
                    queue.offer(new int[]{r, c, color});
                }
            }
        }

        return res;
    }
}