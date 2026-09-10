class Solution {
    
    long [][]dp;
    public long maxAlternatingSum(int[] nums) {
        int n=nums.length;
        
        dp=new long[n][2];
        
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        
        return helper(nums,0,0);
    }
    
    public long helper(int []nums, int p, int idx){
        if(idx>=nums.length){
            return 0;
        }
        
        if(dp[idx][p]!=-1){
            return dp[idx][p];
        }
        
        long max=0;
        if(p%2==0){
            max=helper(nums,1,idx+1)+nums[idx];
        }else{
            max=helper(nums,0,idx+1)-nums[idx];
        }
        
        max=Math.max(max,helper(nums,p,idx+1));
        
        dp[idx][p]=max;
        
        return dp[idx][p];
    }
}