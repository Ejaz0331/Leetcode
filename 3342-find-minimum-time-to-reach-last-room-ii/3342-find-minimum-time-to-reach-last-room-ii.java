class Solution {
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        int[][][] minTime = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(minTime[i][j], Integer.MAX_VALUE);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, 0, 0, 0});
        minTime[0][0][0] = 0;

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int t = curr[0];
            int r = curr[1];
            int c = curr[2];
            int step = curr[3];

            if (r == n - 1 && c == m - 1) {
                return t;
            }

            if (t > minTime[r][c][step]) {
                continue;
            }

            int cost = (step % 2 == 0) ? 1 : 2;
            int nextStep = (step + 1) % 2;

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                    int nextTime = Math.max(t, moveTime[nr][nc]) + cost;
                    if (nextTime < minTime[nr][nc][nextStep]) {
                        minTime[nr][nc][nextStep] = nextTime;
                        pq.offer(new int[]{nextTime, nr, nc, nextStep});
                    }
                }
            }
        }

        return Math.min(minTime[n - 1][m - 1][0], minTime[n - 1][m - 1][1]);
    }
}