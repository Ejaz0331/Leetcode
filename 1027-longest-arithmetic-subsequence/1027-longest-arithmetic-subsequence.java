class Solution {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        
        int maxLen = 2;
        int[][] dpTable = new int[n][1001];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j] + 500;
                dpTable[i][diff] = dpTable[j][diff] > 0 ? dpTable[j][diff] + 1 : 2;
                maxLen = Math.max(maxLen, dpTable[i][diff]);
            }
        }
        
        return maxLen;
    }
}