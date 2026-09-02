class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] memo = new int[n];
        
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        java.util.Arrays.sort(indices, (a, b) -> Integer.compare(arr[a], arr[b]));
        
        int maxJumps = 1;
        for (int i : indices) {
            memo[i] = 1;
            
            for (int j = i + 1; j <= Math.min(n - 1, i + d); j++) {
                if (arr[j] >= arr[i]) {
                    break;
                }
                memo[i] = Math.max(memo[i], 1 + memo[j]);
            }
            
            for (int j = i - 1; j >= Math.max(0, i - d); j--) {
                if (arr[j] >= arr[i]) {
                    break;
                }
                memo[i] = Math.max(memo[i], 1 + memo[j]);
            }
            
            maxJumps = Math.max(maxJumps, memo[i]);
        }
        
        return maxJumps;
    }
}