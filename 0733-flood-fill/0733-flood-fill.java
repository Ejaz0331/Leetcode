class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];

        
        if (originalColor == color) return image;

        dfs(image, sr, sc, originalColor, color);

        return image;
    }

    void dfs(int[][] image, int sr, int sc, int originalColor, int givenColor) {
        int m = image.length;
        int n = image[0].length;

        
        if (sr < 0 || sr >= m || sc < 0 || sc >= n) return;
        if (image[sr][sc] != originalColor) return;

        
        image[sr][sc] = givenColor;

        dfs(image, sr + 1, sc, originalColor, givenColor);
        dfs(image, sr - 1, sc, originalColor, givenColor);
        dfs(image, sr, sc + 1, originalColor, givenColor);
        dfs(image, sr, sc - 1, originalColor, givenColor);
    }
}