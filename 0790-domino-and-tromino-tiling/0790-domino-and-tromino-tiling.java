class Solution {
    public int numTilings(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        
        long[] dp = new long[n + 1];
        long[] f = new long[n + 1];
        
        dp[1] = 1;
        dp[2] = 2;
        f[1] = 0;
        f[2] = 1;
        
        long mod = 1000000007;
        
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + 2 * f[i - 1]) % mod;
            f[i] = (f[i - 1] + dp[i - 2]) % mod;
        }
        
        return (int) dp[n];
    }
}