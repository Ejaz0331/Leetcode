class Solution {
    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] minGrid = new int[m][n];
        int maxScore = Integer.MIN_VALUE;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid.get(i).get(j);
                int minPrev = Integer.MAX_VALUE;
                if (i > 0) {
                    minPrev = Math.min(minPrev, minGrid[i - 1][j]);
                }
                if (j > 0) {
                    minPrev = Math.min(minPrev, minGrid[i][j - 1]);
                }

                if (i > 0 || j > 0) {
                    maxScore = Math.max(maxScore, val - minPrev);
                }

                minGrid[i][j] = Math.min(val, minPrev);
            }
        }

        return maxScore;
    }
}