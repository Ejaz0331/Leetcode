class Solution {
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int count = 0;
        int totalMasks = 1 << n;

        for (int mask = 0; mask < totalMasks; mask++) {
            int[][] dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], 1_000_000_000);
                dist[i][i] = 0;
            }

            for (int[] road : roads) {
                int u = road[0], v = road[1], w = road[2];
                if (((mask >> u) & 1) == 1 && ((mask >> v) & 1) == 1) {
                    dist[u][v] = Math.min(dist[u][v], w);
                    dist[v][u] = Math.min(dist[v][u], w);
                }
            }

            for (int k = 0; k < n; k++) {
                if (((mask >> k) & 1) == 0) continue;
                for (int i = 0; i < n; i++) {
                    if (((mask >> i) & 1) == 0) continue;
                    for (int j = 0; j < n; j++) {
                        if (((mask >> j) & 1) == 0) continue;
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }

            boolean valid = true;
            for (int i = 0; i < n && valid; i++) {
                if (((mask >> i) & 1) == 0) continue;
                for (int j = 0; j < n; j++) {
                    if (((mask >> j) & 1) == 0) continue;
                    if (dist[i][j] > maxDistance) {
                        valid = false;
                        break;
                    }
                }
            }

            if (valid) {
                count++;
            }
        }

        return count;
    }
}