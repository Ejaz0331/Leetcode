class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] dp = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        
        for (int num : nums) {
            int[] nextDp = dp.clone();
            for (int i = 0; i < 3; i++) {
                if (dp[i] != Integer.MIN_VALUE) {
                    int newSum = dp[i] + num;
                    int remainder = newSum % 3;
                    nextDp[remainder] = Math.max(nextDp[remainder], newSum);
                }
            }
            dp = nextDp;
        }
        
        return dp[0] == Integer.MIN_VALUE ? 0 : dp[0];
    }
}