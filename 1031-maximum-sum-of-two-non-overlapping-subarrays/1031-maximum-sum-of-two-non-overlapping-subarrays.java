class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        return Math.max(
            getMax(nums, prefixSum, firstLen, secondLen),
            getMax(nums, prefixSum, secondLen, firstLen)
        );
    }
    
    private int getMax(int[] nums, int[] prefixSum, int L, int M) {
        int n = nums.length;
        int maxL = 0;
        int res = 0;
        
        for (int i = L + M; i <= n; i++) {
            maxL = Math.max(maxL, prefixSum[i - M] - prefixSum[i - M - L]);
            res = Math.max(res, maxL + (prefixSum[i] - prefixSum[i - M]));
        }
        
        return res;
    }
}