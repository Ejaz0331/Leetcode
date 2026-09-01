class Solution {
    public int maximumLength(String s) {
        Map<String, Integer> count = new HashMap<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == s.charAt(i)) {
                    sb.append(s.charAt(j));
                    String sub = sb.toString();
                    count.put(sub, count.getOrDefault(sub, 0) + 1);
                } else {
                    break;
                }
            }
        }

        int maxLength = -1;
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            if (entry.getValue() >= 3) {
                maxLength = Math.max(maxLength, entry.getKey().length());
            }
        }

        return maxLength;
    }
}