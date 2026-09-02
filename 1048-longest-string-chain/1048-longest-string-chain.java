class Solution {
    public int longestStrChain(String[] words) {
        java.util.Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        java.util.Map<String, Integer> map = new java.util.HashMap<>();
        int maxLen = 1;
        
        for (String word : words) {
            int currentLen = 1;
            for (int i = 0; i < word.length(); i++) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                currentLen = Math.max(currentLen, map.getOrDefault(prev, 0) + 1);
            }
            map.put(word, currentLen);
            maxLen = Math.max(maxLen, currentLen);
        }
        
        return maxLen;
    }
}