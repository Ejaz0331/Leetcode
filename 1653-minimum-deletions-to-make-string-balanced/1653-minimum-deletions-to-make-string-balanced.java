class Solution {
    public int minimumDeletions(String s) {
        int bCount = 0;
        int deletions = 0;
        
        for (char c : s.toCharArray()) {
            if (c == 'b') {
                bCount++;
            } else {
                if (bCount > 0) {
                    deletions++;
                    bCount--;
                }
            }
        }
        
        return deletions;
    }
}