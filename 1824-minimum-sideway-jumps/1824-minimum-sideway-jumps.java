class Solution {
    public int minSideJumps(int[] obstacles) {
        int[] dp = new int[] { 1, 0, 1 };
        
        for (int i = 1; i < obstacles.length; i++) {
            int obs = obstacles[i];
            
            if (obs != 0) {
                dp[obs - 1] = 1000000;
            }
            
            for (int j = 0; j < 3; j++) {
                if (obs - 1 == j) continue;
                
                int nextVal = 1000000;
                for (int k = 0; k < 3; k++) {
                    if (k != j && obstacles[i] - 1 != k) {
                        nextVal = Math.min(nextVal, dp[k] + 1);
                    }
                }
                dp[j] = Math.min(dp[j], nextVal);
            }
        }
        
        return Math.min(dp[0], Math.min(dp[1], dp[2]));
    }
}