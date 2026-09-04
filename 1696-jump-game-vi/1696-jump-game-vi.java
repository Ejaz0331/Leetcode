class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        
        int[] deque = new int[n];
        int head = 0, tail = 0;
        deque[tail++] = 0;
        
        for (int i = 1; i < n; i++) {
            if (deque[head] < i - k) {
                head++;
            }
            dp[i] = nums[i] + dp[deque[head]];
            while (head < tail && dp[deque[tail - 1]] <= dp[i]) {
                tail--;
            }
            deque[tail++] = i;
        }
        
        return dp[n - 1];
    }
}