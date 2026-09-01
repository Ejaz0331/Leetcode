class Solution {
    public int maximumLength(String s) {
        List<Integer>[] groups = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            groups[i] = new ArrayList<>();
        }

        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            count++;
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                groups[s.charAt(i) - 'a'].add(count);
                count = 0;
            }
        }

        int maxLength = -1;
        for (int i = 0; i < 26; i++) {
            List<Integer> list = groups[i];
            if (list.isEmpty()) continue;

            list.sort(Collections.reverseOrder());

            int len1 = list.get(0);
            int len2 = list.size() > 1 ? list.get(1) : 0;
            int len3 = list.size() > 2 ? list.get(2) : 0;

            if (len1 >= 3) {
                maxLength = Math.max(maxLength, len1 - 2);
            }
            if (len1 >= 2 && len2 >= len1 - 1) {
                maxLength = Math.max(maxLength, len1 - 1);
            }
            if (len2 >= 1 && len1 >= len2) {
                if (list.size() >= 3) {
                    maxLength = Math.max(maxLength, len3);
                }
            }
        }

        return maxLength;
    }
}