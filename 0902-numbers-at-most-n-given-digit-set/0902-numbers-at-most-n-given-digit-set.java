class Solution {
    public int atMostNGivenDigitSet(String[] digits, int n) {
        String s = Integer.toString(n);
        int k = s.length();
        int count = 0;
        int dLen = digits.length;
        
        for (int i = 1; i < k; i++) {
            int p = 1;
            for (int j = 0; j < i; j++) {
                p *= dLen;
            }
            count += p;
        }
        
        for (int i = 0; i < k; i++) {
            boolean prefixMatch = false;
            char c = s.charAt(i);
            
            for (String digit : digits) {
                char dChar = digit.charAt(0);
                if (dChar < c) {
                    int p = 1;
                    for (int j = 0; j < k - 1 - i; j++) {
                        p *= dLen;
                    }
                    count += p;
                } else if (dChar == c) {
                    prefixMatch = true;
                }
            }
            
            if (!prefixMatch) {
                return count;
            }
        }
        
        return count + 1;
    }
}