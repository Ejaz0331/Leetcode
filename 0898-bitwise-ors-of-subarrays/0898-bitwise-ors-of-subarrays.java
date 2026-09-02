class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        int[] set = new int[arr.length * 32];
        int setSize = 0;
        
        int[] curr = new int[32];
        int currSize = 0;
        
        for (int x : arr) {
            int[] next = new int[32];
            int nextSize = 0;
            
            next[nextSize++] = x;
            for (int i = 0; i < currSize; i++) {
                int val = curr[i] | x;
                if (next[nextSize - 1] != val) {
                    next[nextSize++] = val;
                }
            }
            
            curr = next;
            currSize = nextSize;
            
            for (int i = 0; i < currSize; i++) {
                set[setSize++] = curr[i];
            }
        }
        
        java.util.Arrays.sort(set, 0, setSize);
        int uniqueCount = 0;
        for (int i = 0; i < setSize; i++) {
            if (i == 0 || set[i] != set[i - 1]) {
                uniqueCount++;
            }
        }
        
        return uniqueCount;
    }
}