class Solution {
    public int ways(String[] pizza, int k) {
        int rows = pizza.length;
        int cols = pizza[0].length();
        int MOD = 1_000_000_007;
        
        int[][] apples = new int[rows + 1][cols + 1];
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = cols - 1; c >= 0; c--) {
                apples[r][c] = (pizza[r].charAt(c) == 'A' ? 1 : 0)
                            + apples[r + 1][c]
                            + apples[r][c + 1]
                            - apples[r + 1][c + 1];
            }
        }
        
        Integer[][][] memo = new Integer[rows][cols][k + 1];
        return dp(0, 0, k - 1, rows, cols, apples, memo, MOD);
    }
    
    private int dp(int r, int c, int k, int rows, int cols, int[][] apples, Integer[][][] memo, int MOD) {
        if (apples[r][c] == 0) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        if (memo[r][c][k] != null) {
            return memo[r][c][k];
        }
        
        long ans = 0;
        
        for (int nextR = r + 1; nextR < rows; nextR++) {
            if (apples[r][c] - apples[nextR][c] > 0) {
                ans = (ans + dp(nextR, c, k - 1, rows, cols, apples, memo, MOD)) % MOD;
            }
        }
        
        for (int nextC = c + 1; nextC < cols; nextC++) {
            if (apples[r][c] - apples[r][nextC] > 0) {
                ans = (ans + dp(r, nextC, k - 1, rows, cols, apples, memo, MOD)) % MOD;
            }
        }
        
        return memo[r][c][k] = (int) ans;
    }
}