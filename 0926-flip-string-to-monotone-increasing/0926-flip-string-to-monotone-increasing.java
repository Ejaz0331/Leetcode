class Solution {
    public int minFlipsMonoIncr(String s) {
        int flips = 0;
        int ones = 0;
        int n = s.length();
        char[] chars = s.toCharArray();
        
        for (int i = 0; i < n; i++) {
            if (chars[i] == '1') {
                ones++;
            } else {
                flips = Math.min(flips + 1, ones);
            }
        }
        
        return flips;
    }
}