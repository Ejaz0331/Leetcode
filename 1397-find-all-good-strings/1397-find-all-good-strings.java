class Solution {
    public int findGoodStrings(int n, String s1, String s2, String evil) {
        int MOD = 1_000_000_007;
        int m = evil.length();
        int[] lps = new int[m];
        for (int i = 1, len = 0; i < m; ) {
            if (evil.charAt(i) == evil.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
        
        Integer[][][][] memo = new Integer[n + 1][m + 1][2][2];
        return dp(0, 0, true, true, n, s1, s2, evil, lps, memo, MOD);
    }
    
    private int dp(int idx, int evilMatch, boolean tight1, boolean tight2, 
                   int n, String s1, String s2, String evil, int[] lps, 
                   Integer[][][][] memo, int MOD) {
        if (evilMatch == evil.length()) {
            return 0;
        }
        if (idx == n) {
            return 1;
        }
        
        int t1 = tight1 ? 1 : 0;
        int t2 = tight2 ? 1 : 0;
        if (memo[idx][evilMatch][t1][t2] != null) {
            return memo[idx][evilMatch][t1][t2];
        }
        
        char minChar = tight1 ? s1.charAt(idx) : 'a';
        char maxChar = tight2 ? s2.charAt(idx) : 'z';
        
        long ans = 0;
        for (char c = minChar; c <= maxChar; c++) {
            boolean nextTight1 = tight1 && (c == minChar);
            boolean nextTight2 = tight2 && (c == maxChar);
            
            int nextEvilMatch = evilMatch;
            while (nextEvilMatch > 0 && evil.charAt(nextEvilMatch) != c) {
                nextEvilMatch = lps[nextEvilMatch - 1];
            }
            if (evil.charAt(nextEvilMatch) == c) {
                nextEvilMatch++;
            }
            
            ans = (ans + dp(idx + 1, nextEvilMatch, nextTight1, nextTight2, n, s1, s2, evil, lps, memo, MOD)) % MOD;
        }
        
        return memo[idx][evilMatch][t1][t2] = (int) ans;
    }
}