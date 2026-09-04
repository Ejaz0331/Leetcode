class Solution {
    public int maxValue(int[][] events, int k) {
        java.util.Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int n = events.length;
        int[][] memo = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                memo[i][j] = -1;
            }
        }
        return dfs(0, k, events, memo);
    }
    
    private int dfs(int i, int count, int[][] events, int[][] memo) {
        if (count == 0 || i == events.length) {
            return 0;
        }
        if (memo[i][count] != -1) {
            return memo[i][count];
        }
        
        int nextIndex = binarySearch(events, events[i][1]);
        int include = events[i][2] + dfs(nextIndex, count - 1, events, memo);
        int skip = dfs(i + 1, count, events, memo);
        
        return memo[i][count] = Math.max(include, skip);
    }
    
    private int binarySearch(int[][] events, int endTime) {
        int low = 0, high = events.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (events[mid][0] > endTime) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}