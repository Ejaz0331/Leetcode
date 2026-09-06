class Solution {
public:
    int solve(int i, int j, int m, int n, int k,
              vector<vector<vector<int>>>& dp,
              vector<vector<int>>& coins) {

        if (i >= m || j >= n)
            return INT_MIN / 2;

        if (dp[i][j][k] != INT_MIN)
            return dp[i][j][k];

        if (i == m - 1 && j == n - 1) {
            if (coins[i][j] < 0 && k > 0)
                return 0;
            return coins[i][j];
        }

        int ans = coins[i][j] +
                  max(solve(i + 1, j, m, n, k, dp, coins),
                      solve(i, j + 1, m, n, k, dp, coins));

        if (coins[i][j] < 0 && k > 0) {
            ans = max(ans,
                      max(solve(i + 1, j, m, n, k - 1, dp, coins),
                          solve(i, j + 1, m, n, k - 1, dp, coins)));
        }

        return dp[i][j][k] = ans;
    }

    int maximumAmount(vector<vector<int>>& coins) {
        int m = coins.size();
        int n = coins[0].size();

        vector<vector<vector<int>>> dp(
            m, vector<vector<int>>(n, vector<int>(3, INT_MIN))
        );

        return solve(0, 0, m, n, 2, dp, coins);
    }
};