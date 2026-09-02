class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[] dp = new int[n];
        
        for (int j = 0; j < n; j++) {
            dp[j] = matrix[0][j];
        }
        
        for (int i = 1; i < n; i++) {
            int[] nextDp = new int[n];
            for (int j = 0; j < n; j++) {
                int minPrev = dp[j];
                if (j > 0) {
                    minPrev = Math.min(minPrev, dp[j - 1]);
                }
                if (j < n - 1) {
                    minPrev = Math.min(minPrev, dp[j + 1]);
                }
                nextDp[j] = matrix[i][j] + minPrev;
            }
            dp = nextDp;
        }
        
        int minSum = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            minSum = Math.min(minSum, dp[j]);
        }
        
        return minSum;
    }
}