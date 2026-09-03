class Solution {
    public int countTexts(String pressedKeys) {
        int n = pressedKeys.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        int mod = 1_000_000_007;
        
        for (int i = 1; i <= n; i++) {
            char c = pressedKeys.charAt(i - 1);
            int maxLen = (c == '7' || c == '9') ? 4 : 3;
            
            for (int j = 1; j <= maxLen && i - j >= 0; j++) {
                if (pressedKeys.charAt(i - j) == c) {
                    dp[i] = (dp[i] + dp[i - j]) % mod;
                } else {
                    break;
                }
            }
        }
        
        return (int) dp[n];
    }
}