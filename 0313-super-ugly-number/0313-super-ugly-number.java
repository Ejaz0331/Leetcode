class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int primeLen = primes.length;
        long[] t = new long[n + 1];
        t[1] = 1;
        
        int iPrimes[] = new int[primeLen];
        java.util.Arrays.fill(iPrimes, 1);
        
        for (int i = 2; i <= n; i++) {
            long[] ith_Ugly = new long[primeLen];
            for (int j = 0; j < primeLen; j++) {
                ith_Ugly[j] = t[iPrimes[j]] * primes[j];
            }

            long minUgly = Long.MAX_VALUE;
            for (int j = 0; j < primeLen; j++) {
                minUgly = (ith_Ugly[j] < minUgly) ? ith_Ugly[j] : minUgly;
            }
            t[i] = minUgly;

            for (int j = 0; j < primeLen; j++) {
                if (minUgly == ith_Ugly[j]) {
                    iPrimes[j]++;
                }
            }
        }
        return (int) t[n];
    }
}