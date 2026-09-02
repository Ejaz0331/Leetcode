class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        
        java.util.Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        
        int[] dp = new int[envelopes.length];
        int len = 0;
        
        for (int[] envelope : envelopes) {
            int height = envelope[1];
            int left = 0, right = len;
            
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (dp[mid] < height) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            dp[left] = height;
            if (left == len) {
                len++;
            }
        }
        
        return len;
    }
}