class Solution {
    public boolean[] canMakePalindromeQueries(String s, int[][] queries) {
        int n = s.length();
        int m = n / 2;
        int[][] pref1 = new int[m + 1][26];
        int[][] pref2 = new int[m + 1][26];
        int[] diff = new int[m + 1];

        for (int i = 0; i < m; i++) {
            for (int ch = 0; ch < 26; ch++) {
                pref1[i + 1][ch] = pref1[i][ch];
                pref2[i + 1][ch] = pref2[i][ch];
            }
            pref1[i + 1][s.charAt(i) - 'a']++;
            pref2[i + 1][s.charAt(n - 1 - i) - 'a']++;
            diff[i + 1] = diff[i] + (s.charAt(i) != s.charAt(n - 1 - i) ? 1 : 0);
        }

        boolean[] ans = new boolean[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int a = queries[q][0];
            int b = queries[q][1];
            int c = n - 1 - queries[q][3];
            int d = n - 1 - queries[q][2];

            ans[q] = check(a, b, c, d, pref1, pref2, diff, m);
        }

        return ans;
    }

    private boolean check(int a, int b, int c, int d, int[][] p1, int[][] p2, int[] diff, int m) {
        if (hasDiff(diff, 0, Math.min(a, c) - 1) || hasDiff(diff, Math.max(b, d) + 1, m - 1)) {
            return false;
        }

        if (b < c) {
            if (hasDiff(diff, b + 1, c - 1)) return false;
            return same(get(p1, a, b), get(p2, a, b)) && same(get(p1, c, d), get(p2, c, d));
        }

        if (d < a) {
            if (hasDiff(diff, d + 1, a - 1)) return false;
            return same(get(p1, d, c), get(p2, d, c)) && same(get(p1, a, b), get(p2, a, b));
        }

        if (a <= c && d <= b) {
            int[] rem = sub(get(p1, a, b), add(get(p2, a, c - 1), get(p2, d + 1, b)));
            return rem != null && same(rem, get(p2, c, d));
        }

        if (c <= a && b <= d) {
            int[] rem = sub(get(p2, c, d), add(get(p1, c, a - 1), get(p1, b + 1, d)));
            return rem != null && same(rem, get(p1, a, b));
        }

        if (a <= c && b <= d) {
            int[] rem1 = sub(get(p1, a, b), get(p2, a, c - 1));
            int[] rem2 = sub(get(p2, c, d), get(p1, b + 1, d));
            return rem1 != null && rem2 != null && same(rem1, rem2);
        }

        if (c <= a && d <= b) {
            int[] rem2 = sub(get(p2, c, d), get(p1, c, a - 1));
            int[] rem1 = sub(get(p1, a, b), get(p2, d + 1, b));
            return rem1 != null && rem2 != null && same(rem1, rem2);
        }

        return true;
    }

    private boolean hasDiff(int[] diff, int l, int r) {
        if (l > r) return false;
        return diff[r + 1] - diff[l] > 0;
    }

    private int[] get(int[][] p, int l, int r) {
        int[] res = new int[26];
        if (l > r) return res;
        for (int i = 0; i < 26; i++) {
            res[i] = p[r + 1][i] - p[l][i];
        }
        return res;
    }

    private int[] add(int[] a, int[] b) {
        int[] res = new int[26];
        for (int i = 0; i < 26; i++) {
            res[i] = a[i] + b[i];
        }
        return res;
    }

    private int[] sub(int[] a, int[] b) {
        int[] res = new int[26];
        for (int i = 0; i < 26; i++) {
            res[i] = a[i] - b[i];
            if (res[i] < 0) return null;
        }
        return res;
    }

    private boolean same(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }
}