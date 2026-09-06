class Solution {
    public int minOperations(int[] nums) {
        int operations = 0;
        int currentFlip = 0;
        
        for (int num : nums) {
            if ((num ^ currentFlip) == 0) {
                operations++;
                currentFlip ^= 1;
            }
        }
        
        return operations;
    }
}