class Solution {
    public int maximumANDSum(int[] nums, int numSlots) {
        int maxState = (int) Math.pow(3, numSlots);
        int[] dp = new int[maxState];
        int n = nums.length;
        
        for (int mask = 0; mask < maxState; mask++) {
            int setBits = getSetBits(mask, numSlots);
            if (setBits >= n) continue;
            
            for (int i = 1; i <= numSlots; i++) {
                int slotState = getSlotState(mask, i);
                if (slotState < 2) {
                    int nextMask = mask + getMultiplier(i);
                    dp[nextMask] = Math.max(dp[nextMask], dp[mask] + (nums[setBits] & i));
                }
            }
        }
        
        int ans = 0;
        for (int val : dp) {
            ans = Math.max(ans, val);
        }
        return ans;
    }
    
    private int getSetBits(int mask, int numSlots) {
        int count = 0;
        for (int i = 1; i <= numSlots; i++) {
            count += mask % 3;
            mask /= 3;
        }
        return count;
    }
    
    private int getSlotState(int mask, int slotNum) {
        for (int i = 1; i < slotNum; i++) {
            mask /= 3;
        }
        return mask % 3;
    }
    
    private int getMultiplier(int slotNum) {
        int mult = 1;
        for (int i = 1; i < slotNum; i++) {
            mult *= 3;
        }
        return mult;
    }
}