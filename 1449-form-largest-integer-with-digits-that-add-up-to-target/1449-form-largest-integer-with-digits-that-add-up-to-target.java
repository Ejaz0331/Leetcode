class Solution {
    public String largestNumber(int[] cost, int target) {
        String[] dp = new String[target + 1];
        dp[0] = "";
        
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < 9; j++) {
                int c = cost[j];
                if (i >= c && dp[i - c] != null) {
                    String candidate = (j + 1) + dp[i - c];
                    if (dp[i] == null || candidate.length() > dp[i].length() || 
                        (candidate.length() == dp[i].length() && candidate.compareTo(dp[i]) > 0)) {
                        dp[i] = candidate;
                    }
                }
            }
        }
        
        return dp[target] == null ? "0" : dp[target];
    }
}