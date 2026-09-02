class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLenBefore = new int[n];
        java.util.Arrays.fill(minLenBefore, 1_000_000);
        
        int sum = 0;
        int left = 0;
        int minLenSoFar = 1_000_000;
        int ans = 1_000_000;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left++];
            }
            
            if (sum == target) {
                int currLen = right - left + 1;
                if (left > 0 && minLenBefore[left - 1] != 1_000_000) {
                    ans = Math.min(ans, currLen + minLenBefore[left - 1]);
                }
                minLenSoFar = Math.min(minLenSoFar, currLen);
            }
            minLenBefore[right] = minLenSoFar;
        }
        
        return ans > n ? -1 : ans;
    }
}