class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        if (sum % k != 0) {
            return false;
        }
        
        int target = sum / k;
        
        Integer[] numsBoxed = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsBoxed[i] = nums[i];
        }
        java.util.Arrays.sort(numsBoxed, java.util.Collections.reverseOrder());
        
        if (numsBoxed[0] > target) {
            return false;
        }
        
        boolean[] visited = new boolean[nums.length];
        return dfs(numsBoxed, visited, 0, k, 0, target);
    }
    
    private boolean dfs(Integer[] nums, boolean[] visited, int startIndex, int k, int currentSum, int target) {
        if (k == 1) {
            return true;
        }
        
        if (currentSum == target) {
            return dfs(nums, visited, 0, k - 1, 0, target);
        }
        
        for (int i = startIndex; i < nums.length; i++) {
            if (!visited[i] && currentSum + nums[i] <= target) {
                visited[i] = true;
                if (dfs(nums, visited, i + 1, k, currentSum + nums[i], target)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        
        return false;
    }
}