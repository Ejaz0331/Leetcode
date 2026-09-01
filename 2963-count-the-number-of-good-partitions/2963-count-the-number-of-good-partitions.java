class Solution {
    public int numberOfGoodPartitions(int[] nums) {
        int n = nums.length;
        int mod = 1_000_000_007;

        Map<Integer, Integer> last = new HashMap<>();
        for (int i = 0; i < n; i++) {
            last.put(nums[i], i);
        }

        int segments = 0;
        int maxReach = 0;

        for (int i = 0; i < n; i++) {
            maxReach = Math.max(maxReach, last.get(nums[i]));
            if (i == maxReach) {
                segments++;
            }
        }

        long ans = 1;
        for (int i = 0; i < segments - 1; i++) {
            ans = (ans * 2) % mod;
        }

        return (int) ans;
    }
}