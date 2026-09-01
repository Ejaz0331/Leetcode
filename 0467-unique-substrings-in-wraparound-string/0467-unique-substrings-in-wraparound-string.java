class Solution {
    public int findSubstringInWraproundString(String s) {
        int[] count = new int[26];
        int maxLength = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && (s.charAt(i) - s.charAt(i - 1) == 1 || s.charAt(i - 1) - s.charAt(i) == 25)) {
                maxLength++;
            } else {
                maxLength = 1;
            }
            
            int index = s.charAt(i) - 'a';
            count[index] = Math.max(count[index], maxLength);
        }
        
        int total = 0;
        for (int c : count) {
            total += c;
        }
        
        return total;
    }
}