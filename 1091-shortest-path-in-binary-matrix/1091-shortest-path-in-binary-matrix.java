class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1;

        if (n == 1)
            return 1;

        int[][] dist = new int[n][n];
        for (int[] row : dist)
            Arrays.fill(row, (int)1e9);

        Queue<int[]> q = new LinkedList<>();

        dist[0][0] = 1;
        q.offer(new int[]{0, 0, 1});

        int[] dr = {-1, -1, 0, -1, 1, 1, 0, 1};
        int[] dc = {0, -1, 1, 1, 0, -1, -1, 1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];
            int dis = cur[2];

            for (int i = 0; i < 8; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nc >= 0 && nr < n && nc < n
                        && grid[nr][nc] == 0
                        && dis + 1 < dist[nr][nc]) {

                    dist[nr][nc] = dis + 1;

                    if (nr == n - 1 && nc == n - 1)
                        return dis + 1;

                    q.offer(new int[]{nr, nc, dis + 1});
                }
            }
        }

        return -1;
    }
}