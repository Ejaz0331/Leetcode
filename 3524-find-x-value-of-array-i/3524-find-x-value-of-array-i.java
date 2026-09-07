class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] result  = new long[k];
        int[][] dp = new int[n+1][k];

        for (int i = 0;i<n;i++) {

            dp[i+1][nums[i] % k]++;

            for (int j = 0; j < k; j++) {
                if (dp[i][j] > 0) {
                    int rem = (int) ((long) j * nums[i] % k);
                    dp[i+1][rem] += dp[i][j];
                }
            }

            for (int j = 0; j < k; j++) {
                result[j] += dp[i+1][j];
            }

        }
       

        return result;
    }
}