class Solution {
    public int numDecodings(String s) {
        long e0 = 1, e1 = 0, e2 = 0;
        long mod = 1000000007;
        
        for (char c : s.toCharArray()) {
            long f0 = 0, f1 = 0, f2 = 0;
            if (c == '*') {
                f0 = e0 * 9 + e1 * 9 + e2 * 6;
                f1 = e0;
                f2 = e0;
            } else {
                int digit = c - '0';
                f0 = e0 * (digit > 0 ? 1 : 0) + e1 + e2 * (digit <= 6 ? 1 : 0);
                f1 = (digit == 1 ? e0 : 0);
                f2 = (digit == 2 ? e0 : 0);
            }
            e0 = f0 % mod;
            e1 = f1 % mod;
            e2 = f2 % mod;
        }
        
        return (int) e0;
    }
}