class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        int[][][] memo = new int[n][27][27];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 27; j++) {
                java.util.Arrays.fill(memo[i][j], -1);
            }
        }
        return dp(0, 26, 26, word, memo);
    }
    
    private int dp(int index, int f1, int f2, String word, int[][][] memo) {
        if (index == word.length()) {
            return 0;
        }
        if (memo[index][f1][f2] != -1) {
            return memo[index][f1][f2];
        }
        
        int nextChar = word.charAt(index) - 'A';
        
        int dist1 = getDistance(f1, nextChar) + dp(index + 1, nextChar, f2, word, memo);
        int dist2 = getDistance(f2, nextChar) + dp(index + 1, f1, nextChar, word, memo);
        
        return memo[index][f1][f2] = Math.min(dist1, dist2);
    }
    
    private int getDistance(int a, int b) {
        if (a == 26) return 0;
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}