class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        int maxSum = nums[0];
        
        java.util.Deque<Integer> deque = new java.util.ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            if (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }
            
            dp[i] = nums[i] + (!deque.isEmpty() ? Math.max(0, dp[deque.peekFirst()]) : 0);
            
            maxSum = Math.max(maxSum, dp[i]);
            
            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }
            
            deque.offerLast(i);
        }
        
        return maxSum;
    }
}