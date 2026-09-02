class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        
        int count = 0;
        char[] sChars = s.toCharArray();
        
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            String word = entry.getKey();
            int freq = entry.getValue();
            
            int i = 0, j = 0;
            char[] wChars = word.toCharArray();
            
            while (i < sChars.length && j < wChars.length) {
                if (sChars[i] == wChars[j]) {
                    j++;
                }
                i++;
            }
            
            if (j == wChars.length) {
                count += freq;
            }
        }
        
        return count;
    }
}