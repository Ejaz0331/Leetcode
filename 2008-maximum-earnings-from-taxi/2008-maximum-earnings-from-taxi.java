class Solution {
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a, b) -> Integer.compare(a[1], b[1]));
        
        int m = rides.length;
        long[] dp = new long[m + 1];
        
        for (int i = 0; i < m; i++) {
            int start = rides[i][0];
            int end = rides[i][1];
            long profit = (long) end - start + rides[i][2];
            
            int idx = binarySearch(rides, i, start);
            long include = profit + dp[idx];
            long exclude = dp[i];
            
            dp[i + 1] = Math.max(include, exclude);
        }
        
        return dp[m];
    }
    
    private int binarySearch(int[][] rides, int currentIndex, int target) {
        int low = 0, high = currentIndex - 1;
        int lastIndex = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (rides[mid][1] <= target) {
                lastIndex = mid + 1;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return lastIndex;
    }
}