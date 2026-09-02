class Solution {

    public int countSubstrings(String s, String t) {

        int m = s.length();
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {

                String sub = s.substring(i, j + 1);

                count += subsDiffByOneCharacter(sub, t);
            }
        }

        return count;
    }

    public int subsDiffByOneCharacter(String s, String t) {

        int len = s.length();
        int ans = 0;

        for (int i = 0; i <= t.length() - len; i++) {

            int diff = 0;

            for (int j = 0; j < len; j++) {

                if (s.charAt(j) != t.charAt(i + j)) {
                    diff++;

                    if (diff > 1)
                        break;
                }
            }

            if (diff == 1)
                ans++;
        }

        return ans;
    }
}