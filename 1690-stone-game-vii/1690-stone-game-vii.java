class Solution {
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + stones[i];
        }
        
        int[][] dp = new int[n][n];
        
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                int sumLeft = prefixSum[j + 1] - prefixSum[i + 1];
                int sumRight = prefixSum[j] - prefixSum[i];
                dp[i][j] = Math.max(sumLeft - dp[i + 1][j], sumRight - dp[i][j - 1]);
            }
        }
        
        return dp[0][n - 1];
    }
}