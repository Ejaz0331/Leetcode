class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        java.util.HashMap<Integer, Integer> valMap = new java.util.HashMap<>();
        for (int i = 0; i < n; i++) {
            valMap.put(arr[i], i);
        }
        
        int[][] dp = new int[n][n];
        int maxLen = 0;
        
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < j; i++) {
                int target = arr[j] - arr[i];
                if (target < arr[i] && valMap.containsKey(target)) {
                    int k = valMap.get(target);
                    dp[i][j] = dp[k][i] + 1;
                } else {
                    dp[i][j] = 2;
                }
                maxLen = java.lang.Math.max(maxLen, dp[i][j]);
            }
        }
        
        return maxLen > 2 ? maxLen : 0;
    }
}