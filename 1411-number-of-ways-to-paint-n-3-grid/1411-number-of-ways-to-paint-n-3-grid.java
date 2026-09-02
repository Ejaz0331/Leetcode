class Solution {
    public int numOfWays(int n) {
        long MOD = 1_000_000_007;
        
        long color3 = 6;
        long color2 = 6;
        
        for (int i = 2; i <= n; i++) {
            long nextColor3 = (color3 * 2 + color2 * 2) % MOD;
            long nextColor2 = (color3 * 2 + color2 * 3) % MOD;
            
            color3 = nextColor3;
            color2 = nextColor2;
        }
        
        return (int)((color3 + color2) % MOD);
    }
}