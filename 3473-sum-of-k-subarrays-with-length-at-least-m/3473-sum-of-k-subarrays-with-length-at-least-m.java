class Solution {
    static int l = Integer.MIN_VALUE;
    public int maxSum(int[] nums, int K, int m) {
        int n = nums.length;
        int[] pre = new int[n];
        for (int i = 0; i < n; i++) pre[i] = (i == 0 ? 0 : pre[i - 1]) + nums[i];
        int[][] dp = new int[2][n + 1];
        for (int k = 0; k <= K; k++) {
            int sMax = l;
            for (int i = n; i >= 0; i--) {
                if (i == n) dp[k & 1][i] = (k == 0 ? 0 : l);
                else dp[k & 1][i] = dp[k & 1][i + 1];
                if (i <= n - m && k > 0 && dp[(k - 1) & 1][i + m] != l) {
                    sMax = Math.max(sMax, dp[(k - 1) & 1][i + m] + pre[i + m - 1]);
                    dp[k & 1][i] = Math.max(dp[k & 1][i], sMax - (i == 0 ? 0 : pre[i - 1]));
                }
            }
        }
        return dp[K & 1][0];
    }
}