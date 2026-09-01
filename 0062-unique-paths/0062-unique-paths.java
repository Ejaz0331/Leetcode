class Solution {
    public int uniquePaths(int m, int n) {
        long ans = 1;
        int total = m + n - 2;
        int k = Math.min(m - 1, n - 1);

        for (int i = 1; i <= k; i++) {
            ans = ans * (total - k + i) / i;
        }

        return (int) ans;
    }
}