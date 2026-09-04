class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String doubled = s + s;
        int[] diff1 = new int[2 * n];
        int[] diff2 = new int[2 * n];
        
        for (int i = 0; i < 2 * n; i++) {
            char expected1 = (i % 2 == 0) ? '0' : '1';
            char expected2 = (i % 2 == 0) ? '1' : '0';
            if (doubled.charAt(i) != expected1) diff1[i] = 1;
            if (doubled.charAt(i) != expected2) diff2[i] = 1;
        }
        
        int window1 = 0, window2 = 0;
        for (int i = 0; i < n; i++) {
            window1 += diff1[i];
            window2 += diff2[i];
        }
        
        int ans = Math.min(window1, window2);
        
        for (int i = 0; i < n; i++) {
            window1 += diff1[i + n] - diff1[i];
            window2 += diff2[i + n] - diff2[i];
            ans = Math.min(ans, Math.min(window1, window2));
        }
        
        return ans;
    }
}