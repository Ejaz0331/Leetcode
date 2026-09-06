class Solution {
    public int minMaxSums(int[] nums, int k) {
        int n = nums.length;
        long MOD = 1_000_000_007;
        Arrays.sort(nums);

        long[] fact = new long[n + 1];
        long[] invFact = new long[n + 1];
        fact[0] = 1;
        invFact[0] = 1;

        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        invFact[n] = power(fact[n], MOD - 2, MOD);
        for (int i = n - 1; i >= 1; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }

        long[] pow2 = new long[n + 1];
        pow2[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        long[] sumNCr = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            long sum = 0;
            for (int j = 0; j <= Math.min(i, k - 1); j++) {
                sum = (sum + nCr(i, j, fact, invFact, MOD)) % MOD;
            }
            sumNCr[i] = sum;
        }

        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            int leftCount = i;
            int rightCount = n - 1 - i;

            long waysMin = sumNCr[rightCount];
            long waysMax = sumNCr[leftCount];

            long contribution = (nums[i] * (waysMin + waysMax)) % MOD;
            totalSum = (totalSum + contribution) % MOD;
        }

        return (int) totalSum;
    }

    private long nCr(int n, int r, long[] fact, long[] invFact, long MOD) {
        if (r < 0 || r > n) return 0;
        return fact[n] * invFact[r] % MOD * invFact[n - r] % MOD;
    }

    private long power(long base, long exp, long MOD) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
}