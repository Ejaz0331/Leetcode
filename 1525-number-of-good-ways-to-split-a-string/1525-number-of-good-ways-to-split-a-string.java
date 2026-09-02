class Solution {
    public int numSplits(String s) {
        int n = s.length();
        int[] leftCount = new int[n];
        int[] rightCount = new int[n];
        
        java.util.Set<Character> distinct = new java.util.HashSet<>();
        for (int i = 0; i < n; i++) {
            distinct.add(s.charAt(i));
            leftCount[i] = distinct.size();
        }
        
        distinct.clear();
        for (int i = n - 1; i >= 0; i--) {
            distinct.add(s.charAt(i));
            rightCount[i] = distinct.size();
        }
        
        int goodSplits = 0;
        for (int i = 0; i < n - 1; i++) {
            if (leftCount[i] == rightCount[i + 1]) {
                goodSplits++;
            }
        }
        
        return goodSplits;
    }
}