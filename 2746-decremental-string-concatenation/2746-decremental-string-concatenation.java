class Solution {
    public int minimizeConcatenatedLength(String[] words) {
        int[][][] memo = new int[words.length][26][26];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < 26; j++) {
                for (int l = 0; l < 26; l++) {
                    memo[i][j][l] = -1;
                }
            }
        }
        return words[0].length() + dp(1, words[0].charAt(0) - 'a', words[0].charAt(words[0].length() - 1) - 'a', words, memo);
    }

    private int dp(int i, int first, int last, String[] words, int[][][] memo) {
        if (i == words.length) {
            return 0;
        }
        if (memo[i][first][last] != -1) {
            return memo[i][first][last];
        }

        String w = words[i];
        int wFirst = w.charAt(0) - 'a';
        int wLast = w.charAt(w.length() - 1) - 'a';
        int len = w.length();

        int option1 = len + (last == wFirst ? -1 : 0) + dp(i + 1, first, wLast, words, memo);
        int option2 = len + (wLast == first ? -1 : 0) + dp(i + 1, wFirst, last, words, memo);

        return memo[i][first][last] = Math.min(option1, option2);
    }
}