class Solution {
    public int removeAlmostEqualCharacters(String word) {
        int n = word.length();
        int operations = 0;
        
        for (int i = 1; i < n; i++) {
            char prev = word.charAt(i - 1);
            char curr = word.charAt(i);
            
            if (Math.abs(curr - prev) <= 1) {
                operations++;
                if (i + 1 < n) {
                    char next = word.charAt(i + 1);
                    word = word.substring(0, i) + '#' + word.substring(i + 1);
                } else {
                    word = word.substring(0, i) + '#' + word.substring(i + 1);
                }
            }
        }
        
        return operations;
    }
}