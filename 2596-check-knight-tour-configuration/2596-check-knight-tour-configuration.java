class Solution {
    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }

        int n = grid.length;
        int[][] pos = new int[n * n][2];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                pos[grid[r][c]][0] = r;
                pos[grid[r][c]][1] = c;
            }
        }

        for (int i = 1; i < n * n; i++) {
            int dr = Math.abs(pos[i][0] - pos[i - 1][0]);
            int dc = Math.abs(pos[i][1] - pos[i - 1][1]);

            if (!((dr == 1 && dc == 2) || (dr == 2 && dc == 1))) {
                return false;
            }
        }

        return true;
    }
}