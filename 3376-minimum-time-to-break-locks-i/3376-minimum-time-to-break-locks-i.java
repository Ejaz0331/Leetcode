class Solution {
    public int findMinimumTime(List<Integer> strength, int k) {
        int n = strength.size();
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int mask = 0; mask < (1 << n); mask++) {
            if (dp[mask] == Integer.MAX_VALUE) continue;

            int brokenCount = Integer.bitCount(mask);
            int x = 1 + brokenCount * k;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {
                    int nextMask = mask | (1 << i);
                    int timeToBreak = (strength.get(i) + x - 1) / x;
                    dp[nextMask] = Math.min(dp[nextMask], dp[mask] + timeToBreak);
                }
            }
        }

        return dp[(1 << n) - 1];
    }
}