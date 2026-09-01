class Solution {
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < variables.length; i++) {
            int a = variables[i][0];
            int b = variables[i][1];
            int c = variables[i][2];
            int m = variables[i][3];
            
            int v1 = modPow(a, b, 10);
            int v2 = modPow(v1, c, m);
            
            if (v2 == target) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    private int modPow(int base, int exp, int mod) {
        int res = 1 % mod;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}