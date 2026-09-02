class Solution {
    public int numSubmat(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int[][] left = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            int count = 0;
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1) {
                    count++;
                } else {
                    count = 0;
                }
                left[i][j] = count;
            }
        }
        
        int totalSubmat = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int minWidth = Integer.MAX_VALUE;
                for (int k = i; k < rows; k++) {
                    minWidth = Math.min(minWidth, left[k][j]);
                    if (minWidth == 0) {
                        break;
                    }
                    totalSubmat += minWidth;
                }
            }
        }
        
        return totalSubmat;
    }
}