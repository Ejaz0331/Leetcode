class Solution {
    private final java.util.Map<String, Boolean> memo = new java.util.HashMap<>();

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> wordSet = new HashSet<>();
        for (String word : words) {
            wordSet.add(word);
        }
        
        List<String> result = new ArrayList<>();
        for (String word : words) {
            wordSet.remove(word);
            memo.clear();
            if (canForm(word, wordSet)) {
                result.add(word);
            }
            wordSet.add(word);
        }
        
        return result;
    }
    
    private boolean canForm(String word, Set<String> wordSet) {
        if (memo.containsKey(word)) {
            return memo.get(word);
        }
        if (word.isEmpty()) {
            return true;
        }
        int len = word.length();
        for (int i = 1; i <= len; i++) {
            if (wordSet.contains(word.substring(0, i)) && canForm(word.substring(i), wordSet)) {
                memo.put(word, true);
                return true;
            }
        }
        memo.put(word, false);
        return false;
    }
}