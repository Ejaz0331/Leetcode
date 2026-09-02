class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;
        int oddCount = 0;
        int evenCount = 1;
        int currentSum = 0;
        int totalSubarrays = 0;
        
        for (int num : arr) {
            currentSum += num;
            if (currentSum % 2 == 1) {
                totalSubarrays = (totalSubarrays + evenCount) % MOD;
                oddCount++;
            } else {
                totalSubarrays = (totalSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }
        
        return totalSubarrays;
    }
}