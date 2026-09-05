class Solution {
    public long minIncrementOperations(int[] nums, int k) {
        long dp0 = 0, dp1 = 0, dp2 = 0; 
        if (nums[0] < k){
            dp2 = k - nums[0] ;
        }
        if (nums[1] < k){
            dp1 =  k - nums[1];
        }
        if (nums[2] < k){
            dp0 =  k - nums[2];
        }
        for (int i = 3; i < nums.length; i++){
            long tmp = dp0;
            if (nums[i] < k)  dp0 = k - nums[i]  + min(dp0, min(dp1,dp2));
            else dp0 =  min(dp0, min(dp1,dp2));
            dp2 = dp1;
            dp1 = tmp;
        } 
        return  min(dp0, min(dp1,dp2));
    }

    private long min(long a, long b){
        return a >= b? b:a; 
    }
}