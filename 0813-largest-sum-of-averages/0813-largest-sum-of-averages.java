class Solution {
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[] prefixSum = new double[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        double[] dp = new double[n];
        for (int i = 0; i < n; i++) {
            dp[i] = (prefixSum[n] - prefixSum[i]) / (n - i);
        }
        
        for (int m = 0; m < k - 1; m++) {
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    dp[i] = Math.max(dp[i], (prefixSum[j] - prefixSum[i]) / (j - i) + dp[j]);
                }
            }
        }
        
        return dp[0];
    }
}