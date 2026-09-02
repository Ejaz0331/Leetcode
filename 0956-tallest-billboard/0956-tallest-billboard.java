class Solution {
    public int tallestBillboard(int[] rods) {
        int sum = 0;
        for (int r : rods) {
            sum += r;
        }
        
        int[] dp = new int[sum + 1];
        for (int i = 1; i <= sum; i++) {
            dp[i] = -1;
        }
        dp[0] = 0;
        
        for (int r : rods) {
            int[] nextDp = new int[sum + 1];
            for (int i = 0; i <= sum; i++) {
                nextDp[i] = dp[i];
            }
            
            for (int diff = 0; diff <= sum - r; diff++) {
                if (dp[diff] < 0) continue;
                
                int taller = dp[diff];
                int shorter = taller - diff;
                
                int n1 = taller + r;
                int n2 = shorter;
                int nDiff = n1 - n2;
                if (nDiff <= sum) {
                    nextDp[nDiff] = Math.max(nextDp[nDiff], n1);
                }
                
                int m1 = taller;
                int m2 = shorter + r;
                int mDiff = Math.abs(m1 - m2);
                int mMax = Math.max(m1, m2);
                if (mDiff <= sum) {
                    nextDp[mDiff] = Math.max(nextDp[mDiff], mMax);
                }
            }
            dp = nextDp;
        }
        
        return dp[0];
    }
}