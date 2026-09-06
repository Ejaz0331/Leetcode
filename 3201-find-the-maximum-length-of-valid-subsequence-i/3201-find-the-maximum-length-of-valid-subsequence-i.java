class Solution {
    public int maximumLength(int[] nums) {
        int evenCount = 0, oddCount = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }
        
        int alt1 = 0, alt2 = 0;
        int expected1 = 0;
        for (int num : nums) {
            if (num % 2 == expected1) {
                alt1++;
                expected1 = 1 - expected1;
            }
        }
        
        int expected2 = 1;
        for (int num : nums) {
            if (num % 2 == expected2) {
                alt2++;
                expected2 = 1 - expected2;
            }
        }
        
        int max = Math.max(evenCount, oddCount);
        max = Math.max(max, alt1);
        max = Math.max(max, alt2);
        
        return max;
    }
}