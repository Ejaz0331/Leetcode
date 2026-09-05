class Solution {
    int[][][][][][] dp;
    int k;
    String s;
    
    private void init() {
        for (int i = 0; i < 10; i++)
            for (int t = 0; t < 2; t++)
                for (int l = 0; l < 2; l++)
                    for (int e = 0; e < 10; e++)
                        for (int o = 0; o < 10; o++)
                            Arrays.fill(dp[i][t][l][e][o], -1);
    }
    
    private int rec(int i, int tight, int l, int even, int odd, int remainder) {
        if (i >= s.length()) return (remainder == 0) && (even == odd) ? 1 : 0;
        
        if (dp[i][tight][l][even][odd][remainder] != -1)
            return dp[i][tight][l][even][odd][remainder];
            
        int lmt = tight == 1 ? s.charAt(i) - '0' : 9;
        int ans = 0;
        
        for (int dig = 0; dig <= lmt; dig++) {
            ans += rec(
                i + 1,
                tight == 1 && (dig == lmt) ? 1 : 0,
                l == 1 && (dig == 0) ? 1 : 0,
                even + (dig == 0 ? (l == 0 ? 1 : 0) : ((dig & 1) == 0 ? 1 : 0)),
                odd + ((dig & 1) == 1 ? 1 : 0),
                (int)(((long)Math.pow(10, s.length() - i - 1) * dig) + remainder) % k
            );
        }
        
        return dp[i][tight][l][even][odd][remainder] = ans;
    }
    
    public int numberOfBeautifulIntegers(int low, int high, int k) {
        this.k = k;
        dp = new int[10][2][2][10][10][20];
        
        s = String.valueOf(low - 1);
        init();
        int ansl = rec(0, 1, 1, 0, 0, 0);
        
        s = String.valueOf(high);
        init();
        return rec(0, 1, 1, 0, 0, 0) - ansl;
    }
}