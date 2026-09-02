class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int maxSoFar = arr[0];
        int[] forward = new int[n];
        int[] backward = new int[n];
        
        forward[0] = arr[0];
        for (int i = 1; i < n; i++) {
            forward[i] = Math.max(arr[i], forward[i - 1] + arr[i]);
            maxSoFar = Math.max(maxSoFar, forward[i]);
        }
        
        backward[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            backward[i] = Math.max(arr[i], backward[i + 1] + arr[i]);
        }
        
        int result = maxSoFar;
        for (int i = 1; i < n - 1; i++) {
            result = Math.max(result, forward[i - 1] + backward[i + 1]);
        }
        
        return result;
    }
}