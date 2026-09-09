class Solution {
    private boolean check(String s) {
        for (int i = 0; i < s.length() - 1; i++)
            if (s.charAt(i) == '0' && s.charAt(i + 1) == '1')
                return true;
        return false;
    }

    public int secondsToRemoveOccurrences(String s) {
        int ans = 0;
        if (s.length() < 2) return 0;
        StringBuilder sb = new StringBuilder(s);
        while (check(sb.toString())) {
            ans++;
            int i = 0;
            while (i < sb.length() - 1) {
                if (sb.charAt(i) == '0' && sb.charAt(i + 1) == '1') {
                    sb.setCharAt(i, '1');
                    sb.setCharAt(i + 1, '0');
                    i += 2;
                } else {
                    i++;
                }
            }
        }
        return ans;
    }
}