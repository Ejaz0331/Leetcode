class Solution {
    public int maxRemovals(String source, String pattern, int[] targetIndices) {
        int n = source.length();
        int m = pattern.length();
        
        boolean[] isTarget = new boolean[n];
        for (int idx : targetIndices) {
            isTarget[idx] = true;
        }

        int[][] dp = new int[n + 1][m + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solve(0, 0, source, pattern, isTarget, dp);
    }

    private int solve(int i, int j, String source, String pattern, boolean[] isTarget, int[][] dp) {
        if (j == pattern.length()) {
            int removals = 0;
            for (int k = i; k < source.length(); k++) {
                if (isTarget[k]) removals++;
            }
            return removals;
        }
        if (i == source.length()) {
            return -1_000_000;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int res = -1_000_000;
        if (isTarget[i]) {
            res = Math.max(res, solve(i + 1, j, source, pattern, isTarget, dp));
            res = Math.max(res, 1 + solve(i + 1, j, source, pattern, isTarget, dp));
        } else {
            res = Math.max(res, solve(i + 1, j, source, pattern, isTarget, dp));
        }

        if (source.charAt(i) == pattern.charAt(j)) {
            res = Math.max(res, solve(i + 1, j + 1, source, pattern, isTarget, dp));
        }

        return dp[i][j] = res;
    }
}