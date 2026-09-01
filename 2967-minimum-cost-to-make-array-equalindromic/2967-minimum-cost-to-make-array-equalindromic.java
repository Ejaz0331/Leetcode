class Solution {
    public long minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int median = nums[n / 2];

        int smallerPal = getSmallerPalindrome(median);
        int largerPal = getLargerPalindrome(median);

        long cost1 = calculateCost(nums, smallerPal);
        long cost2 = calculateCost(nums, largerPal);

        return Math.min(cost1, cost2);
    }

    private boolean isPalindrome(int num) {
        String s = Integer.toString(num);
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    private int getSmallerPalindrome(int num) {
        while (!isPalindrome(num)) {
            num--;
        }
        return num;
    }

    private int getLargerPalindrome(int num) {
        while (!isPalindrome(num)) {
            num++;
        }
        return num;
    }

    private long calculateCost(int[] nums, int target) {
        long cost = 0;
        for (int num : nums) {
            cost += Math.abs(num - target);
        }
        return cost;
    }
}