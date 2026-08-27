class Solution {
    public int waysToMakeFair(int[] nums) {
        int totalEven = 0, totalOdd = 0;
        for (int i = 0; i < nums.length; i++)
            if ((i & 1) == 0) totalEven += nums[i];
            else totalOdd += nums[i];

        int leftEven = 0, leftOdd = 0, ans = 0;

        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 0) totalEven -= nums[i];
            else totalOdd -= nums[i];

            if (leftEven + totalOdd == leftOdd + totalEven)
                ans++;

            if ((i & 1) == 0) leftEven += nums[i];
            else leftOdd += nums[i];
        }
        return ans;
    }
}