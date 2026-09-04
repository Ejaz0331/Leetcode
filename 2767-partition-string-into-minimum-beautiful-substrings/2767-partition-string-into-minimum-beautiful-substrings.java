class Solution {
    public int minimumBeautifulSubstrings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = 20;
        }
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) == '0') continue;
            for (int j = 0; j < i; j++) {
                if (s.charAt(j) == '0') continue;
                String sub = s.substring(j, i);
                if (isPowerOfFive(sub)) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        
        return dp[n] > 15 ? -1 : dp[n];
    }
    
    private boolean isPowerOfFive(String s) {
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            val = val * 2 + (s.charAt(i) - '0');
        }
        if (val == 0) return false;
        while (val % 5 == 0) {
            val /= 5;
        }
        return val == 1;
    }
}