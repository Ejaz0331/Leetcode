class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        java.util.Map<Integer, Integer> dp = new java.util.HashMap<>();
        int maxLength = 0;
        
        for (int num : arr) {
            int prev = dp.getOrDefault(num - difference, 0);
            dp.put(num, prev + 1);
            maxLength = Math.max(maxLength, dp.get(num));
        }
        
        return maxLength;
    }
}