class Solution {
    public long minCost(int n, int[][] cost) {
        long[][][] dp = new long[2][4][4];
        
        for (int c1 = 1; c1 <= 3; c1++) {
            for (int c2 = 1; c2 <= 3; c2++) {
                if (c1 != c2) {
                    dp[0][c1][c2] = cost[0][c1 - 1] + cost[n - 1][c2 - 1];
                } else {
                    dp[0][c1][c2] = Long.MAX_VALUE / 2;
                }
            }
        }
        
        int totalPairs = n / 2;
        
        for (int i = 1; i < totalPairs; i++) {
            int curr = i % 2;
            int prev = 1 - curr;
            
            for (int c1 = 1; c1 <= 3; c1++) {
                for (int c2 = 1; c2 <= 3; c2++) {
                    dp[curr][c1][c2] = Long.MAX_VALUE / 2;
                }
            }
            
            for (int prevC1 = 1; prevC1 <= 3; prevC1++) {
                for (int prevC2 = 1; prevC2 <= 3; prevC2++) {
                    long prevCost = dp[prev][prevC1][prevC2];
                    if (prevCost >= Long.MAX_VALUE / 2) continue;
                    
                    for (int c1 = 1; c1 <= 3; c1++) {
                        if (c1 == prevC1) continue;
                        
                        for (int c2 = 1; c2 <= 3; c2++) {
                            if (c2 == prevC2 || c2 == c1) continue;
                            
                            long newCost = prevCost + cost[i][c1 - 1] + cost[n - 1 - i][c2 - 1];
                            dp[curr][c1][c2] = Math.min(dp[curr][c1][c2], newCost);
                        }
                    }
                }
            }
        }
        
        long minAns = Long.MAX_VALUE;
        int lastIdx = (totalPairs - 1) % 2;
        
        for (int c1 = 1; c1 <= 3; c1++) {
            for (int c2 = 1; c2 <= 3; c2++) {
                minAns = Math.min(minAns, dp[lastIdx][c1][c2]);
            }
        }
        
        return minAns;
    }
}