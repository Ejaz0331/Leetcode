class Solution {
    public long maximumTotalCost(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        
        long dp0 = nums[0]; 
        long dp1 = Long.MIN_VALUE; 
        
        for (int i = 1; i < n; i++) {
            long newDp0 = Math.max(dp0, dp1) + nums[i];
            long newDp1 = dp0 - nums[i];
            
            dp0 = newDp0;
            dp1 = newDp1;
        }
        
        return Math.max(dp0, dp1);
    }
}