class Solution {
    public int minDeletionSize(String[] strs) {
        int m = strs.length;
        int n = strs[0].length();
        int[] dp = new int[n];
        int maxLen = 0;
        
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
            for (int i = 0; i < j; i++) {
                boolean valid = true;
                for (int r = 0; r < m; r++) {
                    if (strs[r].charAt(i) > strs[r].charAt(j)) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[j]);
        }
        
        return n - maxLen;
    }
}