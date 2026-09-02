class Solution {
    public int maxStudents(char[][] seats) {
        int m = seats.length;
        int n = seats[0].length;
        int[] validRows = new int[m];
        
        for (int i = 0; i < m; i++) {
            int rowMask = 0;
            for (int j = 0; j < n; j++) {
                if (seats[i][j] == '.') {
                    rowMask |= (1 << j);
                }
            }
            validRows[i] = rowMask;
        }
        
        int[] dp = new int[1 << n];
        java.util.Arrays.fill(dp, -1);
        dp[0] = 0;
        
        for (int i = 0; i < m; i++) {
            int[] nextDp = new int[1 << n];
            java.util.Arrays.fill(nextDp, -1);
            
            for (int prevMask = 0; prevMask < (1 << n); prevMask++) {
                if (dp[prevMask] == -1) continue;
                
                for (int currMask = 0; currMask < (1 << n); currMask++) {
                    if ((currMask & validRows[i]) != currMask) continue;
                    if ((currMask & (currMask << 1)) != 0 || (currMask & (currMask >> 1)) != 0) continue;
                    if ((currMask & (prevMask << 1)) != 0 || (currMask & (prevMask >> 1)) != 0) continue;
                    
                    int students = Integer.bitCount(currMask);
                    nextDp[currMask] = Math.max(nextDp[currMask], dp[prevMask] + students);
                }
            }
            dp = nextDp;
        }
        
        int maxStudents = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            maxStudents = Math.max(maxStudents, dp[mask]);
        }
        
        return maxStudents;
    }
}