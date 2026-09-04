class Solution {
    public int longestString(int x, int y, int z) {
        if (x == y) {
            return 2 * (x + y + z);
        } else {
            return 2 * (2 * Math.min(x, y) + 1 + z);
        }
    }
}