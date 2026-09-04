class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int minSum = 0, maxSum = 0;
        int currentMin = 0, currentMax = 0;
        for (int x : nums) {
            currentMax = Math.max(0, currentMax + x);
            maxSum = Math.max(maxSum, currentMax);
            currentMin = Math.min(0, currentMin + x);
            minSum = Math.min(minSum, currentMin);
        }
        return Math.max(maxSum, -minSum);
    }
}