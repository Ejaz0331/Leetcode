class Solution {
    public long maxScore(int[] a, int[] b) {
        int n = b.length;
        long[] dp = new long[4];
        for (int i = 0; i < 4; i++) {
            dp[i] = Long.MIN_VALUE / 2;
        }

        for (int j = 0; j < n; j++) {
            long newDp3 = Math.max(dp[3], dp[2] + (long) a[3] * b[j]);
            long newDp2 = Math.max(dp[2], dp[1] + (long) a[2] * b[j]);
            long newDp1 = Math.max(dp[1], dp[0] + (long) a[1] * b[j]);
            long newDp0 = Math.max(dp[0], (long) a[0] * b[j]);

            dp[3] = newDp3;
            dp[2] = newDp2;
            dp[1] = newDp1;
            dp[0] = newDp0;
        }

        return dp[3];
    }
}