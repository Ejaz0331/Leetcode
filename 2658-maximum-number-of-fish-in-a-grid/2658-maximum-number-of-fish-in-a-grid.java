class Solution {
    public int findMaxFish(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxFish = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] > 0) {
                    maxFish = Math.max(maxFish, dfs(grid, r, c, m, n));
                }
            }
        }

        return maxFish;
    }

    private int dfs(int[][] grid, int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return 0;
        }

        int fish = grid[r][c];
        grid[r][c] = 0;

        fish += dfs(grid, r + 1, c, m, n);
        fish += dfs(grid, r - 1, c, m, n);
        fish += dfs(grid, r, c + 1, m, n);
        fish += dfs(grid, r, c - 1, m, n);

        return fish;
    }
}