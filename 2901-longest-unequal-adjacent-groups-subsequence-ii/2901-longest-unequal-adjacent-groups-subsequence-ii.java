class Solution {
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        int[] dp = new int[n];
        int[] prev = new int[n];
        int maxLen = 0;
        int maxIdx = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (groups[i] != groups[j] && words[i].length() == words[j].length()) {
                    int diffCount = 0;
                    for (int k = 0; k < words[i].length(); k++) {
                        if (words[i].charAt(k) != words[j].charAt(k)) {
                            diffCount++;
                        }
                    }
                    if (diffCount == 1 && dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIdx = i;
            }
        }

        List<String> result = new ArrayList<>();
        int curr = maxIdx;
        while (curr != -1) {
            result.add(0, words[curr]);
            curr = prev[curr];
        }

        return result;
    }
}