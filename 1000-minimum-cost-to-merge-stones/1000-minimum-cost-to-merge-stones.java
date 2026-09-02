class Solution {
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) {
            return -1;
        }

        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + stones[i];
        }

        int[][][] dp = new int[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int m = 1; m <= k; m++) {
                    dp[i][j][m] = 1000000000;
                }
            }
            dp[i][i][1] = 0;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                for (int m = 2; m <= k; m++) {
                    for (int mid = i; mid < j; mid += k - 1) {
                        dp[i][j][m] = Math.min(dp[i][j][m], dp[i][mid][1] + dp[mid + 1][j][m - 1]);
                    }
                }
                dp[i][j][1] = dp[i][j][k] + (prefixSum[j + 1] - prefixSum[i]);
            }
        }

        return dp[0][n - 1][1];
    }
}