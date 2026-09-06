class Solution {
    public int lengthAfterTransformations(String s, int t) {
        long MOD = 1_000_000_007;
        long[] count = new long[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (int step = 0; step < t; step++) {
            long[] nextCount = new long[26];
            for (int i = 0; i < 25; i++) {
                nextCount[i + 1] = count[i];
            }
            nextCount[0] = (nextCount[0] + count[25]) % MOD;
            nextCount[1] = (nextCount[1] + count[25]) % MOD;
            count = nextCount;
        }

        long totalLength = 0;
        for (long c : count) {
            totalLength = (totalLength + c) % MOD;
        }

        return (int) totalLength;
    }
}