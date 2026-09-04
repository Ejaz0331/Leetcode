class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        dp[0] = true;
        int count = 0;
        
        for (int i = 1; i < n; i++) {
            if (i >= minJump && dp[i - minJump]) {
                count++;
            }
            if (i > maxJump && dp[i - maxJump - 1]) {
                count--;
            }
            if (count > 0 && s.charAt(i) == '0') {
                dp[i] = true;
            }
        }
        
        return dp[n - 1];
    }
}