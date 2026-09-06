class Solution {
    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int n = rewardValues.length;
        int maxVal = rewardValues[n - 1];
        
        boolean[] dp = new boolean[2 * maxVal];
        dp[0] = true;
        
        for (int v : rewardValues) {
            for (int x = 2 * v - 1; x >= v; x--) {
                if (dp[x - v]) {
                    dp[x] = true;
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < 2 * maxVal; i++) {
            if (dp[i]) {
                ans = i;
            }
        }
        
        return ans;
    }
}