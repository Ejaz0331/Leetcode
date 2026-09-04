class Solution {
    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int len1 = 1, len2 = 1;
        int maxLen = 1;
        
        for (int i = 1; i < n; i++) {
            int nextLen1 = 1, nextLen2 = 1;
            
            if (nums1[i] >= nums1[i - 1]) {
                nextLen1 = Math.max(nextLen1, len1 + 1);
            }
            if (nums1[i] >= nums2[i - 1]) {
                nextLen1 = Math.max(nextLen1, len2 + 1);
            }
            
            if (nums2[i] >= nums1[i - 1]) {
                nextLen2 = Math.max(nextLen2, len1 + 1);
            }
            if (nums2[i] >= nums2[i - 1]) {
                nextLen2 = Math.max(nextLen2, len2 + 1);
            }
            
            len1 = nextLen1;
            len2 = nextLen2;
            maxLen = Math.max(maxLen, Math.max(len1, len2));
        }
        
        return maxLen;
    }
}