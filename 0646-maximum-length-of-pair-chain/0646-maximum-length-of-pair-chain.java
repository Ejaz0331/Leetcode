class Solution {
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0) {
            return 0;
        }
        
        java.util.Arrays.sort(pairs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int count = 0;
        int prevEnd = Integer.MIN_VALUE;
        
        for (int[] pair : pairs) {
            if (pair[0] > prevEnd) {
                count++;
                prevEnd = pair[1];
            }
        }
        
        return count;
    }
}