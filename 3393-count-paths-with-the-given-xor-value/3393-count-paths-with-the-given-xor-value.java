class Solution {
    private static final int MOD = 1000000007;
    
    public int countPathsWithXorValue(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][16];
        dp[0][0][grid[0][0]] = 1;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    for (int prevXor = 0; prevXor < 16; prevXor++) {
                        int count = dp[i - 1][j][prevXor];
                        if (count != 0) {
                            int newXor = prevXor ^ grid[i][j];
                            dp[i][j][newXor] = (int)((dp[i][j][newXor] + (long)count) % MOD);
                        }
                    }
                }
                if (j > 0) {
                    for (int prevXor = 0; prevXor < 16; prevXor++) {
                        int count = dp[i][j - 1][prevXor];
                        if (count != 0) {
                            int newXor = prevXor ^ grid[i][j];
                            dp[i][j][newXor] = (int)((dp[i][j][newXor] + (long)count) % MOD);
                        }
                    }
                }
            }
        }
        return dp[m - 1][n - 1][k] % MOD;
    }
}