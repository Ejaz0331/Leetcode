class Solution {
    public int numberOfWays(int n, int x) {
        int mod = 1_000_000_007;
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; ; i++) {
            long power = 1;
            for (int k = 0; k < x; k++) {
                power *= i;
            }
            int p = (int) power;
            if (p > n) {
                break;
            }
            for (int j = n; j >= p; j--) {
                dp[j] = (dp[j] + dp[j - p]) % mod;
            }
        }

        return dp[n];
    }
}