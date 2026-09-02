class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        
        int[][] dp = new int[n + 2][n + 2];
        
        for (int len = 1; len <= n; len++) {
            for (int left = 1; left <= n - len + 1; left++) {
                int right = left + len - 1;
                for (int i = left; i <= right; i++) {
                    dp[left][right] = Math.max(
                        dp[left][right],
                        arr[left - 1] * arr[i] * arr[right + 1] + dp[left][i - 1] + dp[i + 1][right]
                    );
                }
            }
        }
        
        return dp[1][n];
    }
}