class Solution {
    private int[][] memo;

    public int maxOperations(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;
        
        memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        
        int op1 = 1 + helper(nums, 2, n - 1, nums[0] + nums[1]);
        
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        int op2 = 1 + helper(nums, 0, n - 3, nums[n - 2] + nums[n - 1]);
        
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        int op3 = 1 + helper(nums, 1, n - 2, nums[0] + nums[n - 1]);
        
        return Math.max(op1, Math.max(op2, op3));
    }

    private int helper(int[] nums, int l, int r, int target) {
        if (l >= r) return 0;
        if (memo[l][r] != -1) return memo[l][r];
        
        int res = 0;
        if (l + 1 <= r && nums[l] + nums[l + 1] == target) {
            res = Math.max(res, 1 + helper(nums, l + 2, r, target));
        }
        if (l <= r - 1 && nums[r - 1] + nums[r] == target) {
            res = Math.max(res, 1 + helper(nums, l, r - 2, target));
        }
        if (nums[l] + nums[r] == target) {
            res = Math.max(res, 1 + helper(nums, l + 1, r - 1, target));
        }
        
        return memo[l][r] = res;
    }
}