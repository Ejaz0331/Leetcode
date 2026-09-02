class Solution {
    public int countVowelPermutation(int n) {
        long MOD = 1_000_000_007;
        
        long a = 1, e = 1, i = 1, o = 1, u = 1;
        
        for (int len = 2; len <= n; len++) {
            long nextA = (e + i + u) % MOD;
            long nextE = (a + i) % MOD;
            long nextI = (e + o) % MOD;
            long nextO = i % MOD;
            long nextU = (i + o) % MOD;
            
            a = nextA;
            e = nextE;
            i = nextI;
            o = nextO;
            u = nextU;
        }
        
        long total = (a + e + i + o + u) % MOD;
        return (int) total;
    }
}