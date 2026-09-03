class Solution {
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int n = events.length;
        int[] suffixMax = new int[n + 1];
        
        for (int i = n - 1; i >= 0; i--) {
            suffixMax[i] = Math.max(suffixMax[i + 1], events[i][2]);
        }
        
        int maxSum = 0;
        
        for (int i = 0; i < n; i++) {
            maxSum = Math.max(maxSum, events[i][2]);
            
            int low = i + 1, high = n - 1;
            int nextIndex = n;
            
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (events[mid][0] > events[i][1]) {
                    nextIndex = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            
            if (nextIndex < n) {
                maxSum = Math.max(maxSum, events[i][2] + suffixMax[nextIndex]);
            }
        }
        
        return maxSum;
    }
}