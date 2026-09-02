class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        long MOD = 1_000_000_007;
        long maxKadane1 = kadane(arr);
        
        if (k == 1) {
            return (int)(maxKadane1 % MOD);
        }
        
        long totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }
        
        long maxPrefix = 0, currentPrefix = 0;
        for (int num : arr) {
            currentPrefix += num;
            maxPrefix = Math.max(maxPrefix, currentPrefix);
        }
        
        long maxSuffix = 0, currentSuffix = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            currentSuffix += arr[i];
            maxSuffix = Math.max(maxSuffix, currentSuffix);
        }
        
        long ans = 0;
        if (totalSum > 0) {
            ans = Math.max(maxKadane1, maxPrefix + maxSuffix + (k - 2) * totalSum);
        } else {
            ans = Math.max(maxKadane1, maxPrefix + maxSuffix);
        }
        
        return (int)(Math.max(0, ans) % MOD);
    }
    
    private long kadane(int[] arr) {
        long maxSoFar = 0;
        long currentMax = 0;
        for (int num : arr) {
            currentMax = Math.max(num, currentMax + num);
            maxSoFar = Math.max(maxSoFar, currentMax);
        }
        return maxSoFar;
    }
}