class Solution {
    public int minArraySum(int[] nums, int k, int op1, int op2) {
        int n = nums.length;
        int[][][] dp = new int[n + 1][op1 + 1][op2 + 1];

        for (int i = 0; i <= n; i++) {
            for (int o1 = 0; o1 <= op1; o1++) {
                Arrays.fill(dp[i][o1], -1);
            }
        }

        return solve(0, op1, op2, nums, k, dp);
    }

    private int solve(int i, int o1, int o2, int[] nums, int k, int[][][] dp) {
        if (i == nums.length) {
            return 0;
        }
        if (dp[i][o1][o2] != -1) {
            return dp[i][o1][o2];
        }

        int val = nums[i];
        int res = val + solve(i + 1, o1, o2, nums, k, dp);

        if (o1 > 0) {
            int nextVal = (val + 1) / 2;
            res = Math.min(res, nextVal + solve(i + 1, o1 - 1, o2, nums, k, dp));
        }

        if (o2 > 0 && val >= k) {
            int nextVal = val - k;
            res = Math.min(res, nextVal + solve(i + 1, o1, o2 - 1, nums, k, dp));
        }

        if (o1 > 0 && o2 > 0) {
            int nextVal1 = (val + 1) / 2;
            if (nextVal1 >= k) {
                int nextVal2 = nextVal1 - k;
                res = Math.min(res, nextVal2 + solve(i + 1, o1 - 1, o2 - 1, nums, k, dp));
            }
        }

        if (o1 > 0 && o2 > 0 && val >= k) {
            int nextVal1 = val - k;
            int nextVal2 = (nextVal1 + 1) / 2;
            res = Math.min(res, nextVal2 + solve(i + 1, o1 - 1, o2 - 1, nums, k, dp));
        }

        return dp[i][o1][o2] = res;
    }
}