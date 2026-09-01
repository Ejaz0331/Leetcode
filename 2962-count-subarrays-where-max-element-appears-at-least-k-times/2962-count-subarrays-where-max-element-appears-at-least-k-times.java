class Solution {
    public long countSubarrays(int[] nums, int k) {
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        long ans = 0;
        int count = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == maxVal) {
                count++;
            }

            while (count == k) {
                if (nums[left] == maxVal) {
                    count--;
                }
                left++;
            }

            ans += left;
        }

        return ans;
    }
}