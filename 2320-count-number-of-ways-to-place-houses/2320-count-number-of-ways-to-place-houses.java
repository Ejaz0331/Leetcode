class Solution {
    int mod = 1000000007;
    public int countHousePlacements(int n) {
        int dp[][]=new int[n+1][2];
        for(int i=0;i<=n;i++)
        {
            Arrays.fill(dp[i],-1);
        }
        long oneSide = recur(n,0,1,dp);
        return (int)((oneSide*oneSide)%mod);
    }
    public int recur(int n,int idx,int flag,int dp[][])
    {
        if(idx==n)return 1;
        if(dp[idx][flag]!=-1)return dp[idx][flag];
        int ans = recur(n,idx+1,1,dp);
        if(flag==1)
        {
            ans = (ans+recur(n,idx+1,0,dp))%mod;
        }
        return dp[idx][flag]=ans;
    }
}