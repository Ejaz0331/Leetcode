class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;
        
        long[][] maxDp = new long[m][n];
        long[][] minDp = new long[m][n];
        
        maxDp[0][0] = grid[0][0];
        minDp[0][0] = grid[0][0];
        
        for (int j = 1; j < n; j++) {
            maxDp[0][j] = maxDp[0][j - 1] * grid[0][j];
            minDp[0][j] = minDp[0][j - 1] * grid[0][j];
        }
        
        for (int i = 1; i < m; i++) {
            maxDp[i][0] = maxDp[i - 1][0] * grid[i][0];
            minDp[i][0] = minDp[i - 1][0] * grid[i][0];
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long val = grid[i][j];
                long p1 = maxDp[i - 1][j] * val;
                long p2 = minDp[i - 1][j] * val;
                long p3 = maxDp[i][j - 1] * val;
                long p4 = minDp[i][j - 1] * val;
                
                maxDp[i][j] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
                minDp[i][j] = Math.min(Math.min(p1, p2), Math.min(p3, p4));
            }
        }
        
        long result = maxDp[m - 1][n - 1];
        if (result < 0) {
            return -1;
        }
        return (int) (result % MOD);
    }
}