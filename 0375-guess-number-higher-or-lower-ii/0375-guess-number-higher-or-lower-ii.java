class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 2];
        for (int len = 2; len <= n; len++) {
            for (int start = 1; start <= n - len + 1; start++) {
                int end = start + len - 1;
                int minCost = Integer.MAX_VALUE;
                for (int k = start; k <= end; k++) {
                    int cost = k + Math.max(k - 1 >= start ? dp[start][k - 1] : 0, 
                                            k + 1 <= end ? dp[k + 1][end] : 0);
                    minCost = Math.min(minCost, cost);
                }
                dp[start][end] = minCost;
            }
        }
        return dp[1][n];
    }
}