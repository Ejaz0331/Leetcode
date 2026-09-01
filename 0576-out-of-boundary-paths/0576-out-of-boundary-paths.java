class Solution {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if (maxMove <= 0) {
            return 0;
        }
        
        int mod = 1000000007;
        int[][] dp = new int[m][n];
        dp[startRow][startColumn] = 1;
        int count = 0;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        for (int move = 1; move <= maxMove; move++) {
            int[][] temp = new int[m][n];
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (dp[r][c] > 0) {
                        for (int i = 0; i < 4; i++) {
                            int nr = r + dr[i];
                            int nc = c + dc[i];
                            if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                                count = (count + dp[r][c]) % mod;
                            } else {
                                temp[nr][nc] = (temp[nr][nc] + dp[r][c]) % mod;
                            }
                        }
                    }
                }
            }
            dp = temp;
        }
        
        return count;
    }
}