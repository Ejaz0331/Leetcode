class Solution {
    public static int solve(int remaining_idx,int idx,int nums[],int dp[][]){
        if(idx>nums.length)return nums[remaining_idx];
        if(idx==nums.length)return Math.max(nums[remaining_idx],nums[idx-1]); 
        if(dp[remaining_idx][idx]!=-1)return dp[remaining_idx][idx];
        int option1=Math.max(nums[remaining_idx],nums[idx-1])+solve(idx,idx+2,nums,dp);
        int option2=Math.max(nums[remaining_idx],nums[idx])+solve(idx-1,idx+2,nums,dp);
        int option3=Math.max(nums[idx-1],nums[idx])+solve(remaining_idx,idx+2,nums,dp);
        return  dp[remaining_idx][idx]=Math.min(option1,Math.min(option2,option3));

    }
    public int minCost(int[] nums) {
        int dp[][]=new int[nums.length][nums.length];
        for(int i=0;i<nums.length;i++)Arrays.fill(dp[i],-1);
        return solve(0,2,nums,dp);
    }
}