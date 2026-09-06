class Solution {
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int[] count = new int[26];
            int uniqueChars = 0;
            int maxFreq = 0;

            for (int j = i; j >= 0; j--) {
                int idx = s.charAt(j) - 'a';
                if (count[idx] == 0) {
                    uniqueChars++;
                }
                count[idx]++;
                maxFreq = Math.max(maxFreq, count[idx]);

                if ((i - j + 1) == uniqueChars * maxFreq) {
                    dp[i + 1] = Math.min(dp[i + 1], dp[j] + 1);
                }
            }
        }

        return dp[n];
    }
}