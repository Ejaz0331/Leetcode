class Solution {
    public int[] countOfPairs(int n, int x, int y) {
        int[] result = new int[n];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    int directDist = Math.abs(i - j);
                    int viaShortcut = Math.abs(i - x) + 1 + Math.abs(y - j);
                    int viaShortcutRev = Math.abs(i - y) + 1 + Math.abs(x - j);

                    int minDist = Math.min(directDist, Math.min(viaShortcut, viaShortcutRev));

                    result[minDist - 1]++;
                }
            }
        }

        return result;
    }
}