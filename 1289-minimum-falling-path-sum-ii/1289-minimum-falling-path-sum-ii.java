class Solution {
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        if (n == 1) return grid[0][0];
        
        int firstMin = 0;
        int secondMin = 0;
        int firstMinIndex = -1;
        
        for (int i = 0; i < n; i++) {
            int nextFirstMin = Integer.MAX_VALUE;
            int nextSecondMin = Integer.MAX_VALUE;
            int nextFirstMinIndex = -1;
            
            for (int j = 0; j < n; j++) {
                int val = grid[i][j] + (j == firstMinIndex ? secondMin : firstMin);
                
                if (val < nextFirstMin) {
                    nextSecondMin = nextFirstMin;
                    nextFirstMin = val;
                    nextFirstMinIndex = j;
                } else if (val < nextSecondMin) {
                    nextSecondMin = val;
                }
            }
            
            firstMin = nextFirstMin;
            secondMin = nextSecondMin;
            firstMinIndex = nextFirstMinIndex;
        }
        
        return firstMin;
    }
}