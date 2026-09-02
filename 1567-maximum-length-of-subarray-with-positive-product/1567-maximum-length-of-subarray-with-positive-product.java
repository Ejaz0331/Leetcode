class Solution {
    public int getMaxLen(int[] nums) {
        int maxLen = 0;
        int positiveLen = 0;
        int negativeLen = 0;
        
        for (int num : nums) {
            if (num > 0) {
                positiveLen++;
                negativeLen = negativeLen == 0 ? 0 : negativeLen + 1;
            } else if (num < 0) {
                int temp = positiveLen;
                positiveLen = negativeLen == 0 ? 0 : negativeLen + 1;
                negativeLen = temp + 1;
            } else {
                positiveLen = 0;
                negativeLen = 0;
            }
            maxLen = Math.max(maxLen, positiveLen);
        }
        
        return maxLen;
    }
}