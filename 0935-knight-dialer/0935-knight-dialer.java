class Solution {
    public int knightDialer(int n) {
        if (n == 1) {
            return 10;
        }
        
        long mod = 1000000007;
        long[] dp = new long[10];
        for (int i = 0; i < 10; i++) {
            dp[i] = 1;
        }
        
        for (int step = 2; step <= n; step++) {
            long[] nextDp = new long[10];
            nextDp[0] = (dp[4] + dp[6]) % mod;
            nextDp[1] = (dp[6] + dp[8]) % mod;
            nextDp[2] = (dp[7] + dp[9]) % mod;
            nextDp[3] = (dp[4] + dp[8]) % mod;
            nextDp[4] = (dp[0] + dp[3] + dp[9]) % mod;
            nextDp[5] = 0;
            nextDp[6] = (dp[0] + dp[1] + dp[7]) % mod;
            nextDp[7] = (dp[2] + dp[6]) % mod;
            nextDp[8] = (dp[1] + dp[3]) % mod;
            nextDp[9] = (dp[2] + dp[4]) % mod;
            dp = nextDp;
        }
        
        long total = 0;
        for (int i = 0; i < 10; i++) {
            total = (total + dp[i]) % mod;
        }
        
        return (int) total;
    }
}