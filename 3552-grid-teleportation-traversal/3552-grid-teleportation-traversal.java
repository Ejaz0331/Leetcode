class Solution {
    public int minMoves(String[] matrix) {
        int m = matrix.length;
        int n = matrix[0].length();

        if (m == 1 && n == 1) {
            return 0;
        }

        List<int[]>[] portals = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            portals[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = matrix[i].charAt(j);
                if (ch >= 'A' && ch <= 'Z') {
                    portals[ch - 'A'].add(new int[]{i, j});
                }
            }
        }

        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        Deque<int[]> deque = new ArrayDeque<>();
        boolean[] usedPortal = new boolean[26];

        dist[0][0] = 0;
        deque.offer(new int[]{0, 0});

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            int d = dist[r][c];

            if (r == m - 1 && c == n - 1) {
                return d;
            }

            char ch = matrix[r].charAt(c);
            if (ch >= 'A' && ch <= 'Z' && !usedPortal[ch - 'A']) {
                usedPortal[ch - 'A'] = true;
                for (int[] target : portals[ch - 'A']) {
                    int tr = target[0];
                    int tc = target[1];
                    if (dist[tr][tc] == -1 || d < dist[tr][tc]) {
                        dist[tr][tc] = d;
                        deque.offerFirst(new int[]{tr, tc});
                    }
                }
            }

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && matrix[nr].charAt(nc) != '#') {
                    if (dist[nr][nc] == -1 || d + 1 < dist[nr][nc]) {
                        dist[nr][nc] = d + 1;
                        deque.offerLast(new int[]{nr, nc});
                    }
                }
            }
        }

        return dist[m - 1][n - 1];
    }
}