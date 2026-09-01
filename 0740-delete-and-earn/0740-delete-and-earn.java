class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        int[] sum = new int[maxVal + 1];
        for (int num : nums) {
            sum[num] += num;
        }
        
        int rob1 = 0, rob2 = 0;
        for (int i = 0; i <= maxVal; i++) {
            int temp = Math.max(rob1 + sum[i], rob2);
            rob1 = rob2;
            rob2 = temp;
        }
        
        return rob2;
    }
}