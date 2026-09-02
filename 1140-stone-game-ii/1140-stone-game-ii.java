class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        int[][] memo = new int[n][n + 1];
        return dfs(piles, suffixSum, 0, 1, memo);
    }
    
    private int dfs(int[] piles, int[] suffixSum, int i, int m, int[][] memo) {
        if (i + 2 * m >= piles.length) {
            return suffixSum[i];
        }
        
        if (memo[i][m] != 0) {
            return memo[i][m];
        }
        
        int minOpponent = Integer.MAX_VALUE;
        for (int x = 1; x <= 2 * m; x++) {
            minOpponent = Math.min(minOpponent, dfs(piles, suffixSum, i + x, Math.max(m, x), memo));
        }
        
        memo[i][m] = suffixSum[i] - minOpponent;
        return memo[i][m];
    }
}