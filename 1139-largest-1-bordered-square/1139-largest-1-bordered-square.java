class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        int[][] left = new int[rows][cols];
        int[][] up = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                    up[i][j] = (i == 0 ? 0 : up[i - 1][j]) + 1;
                }
            }
        }
        
        for (int size = Math.min(rows, cols); size > 0; size--) {
            for (int i = 0; i <= rows - size; i++) {
                for (int j = 0; j <= cols - size; j++) {
                    if (up[i + size - 1][j] >= size &&
                        up[i + size - 1][j + size - 1] >= size &&
                        left[i][j + size - 1] >= size &&
                        left[i + size - 1][j + size - 1] >= size) {
                        return size * size;
                    }
                }
            }
        }
        
        return 0;
    }
}