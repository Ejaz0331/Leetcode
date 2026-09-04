class Solution {
    int closest;
    
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        closest = baseCosts[0];
        for (int base : baseCosts) {
            dfs(toppingCosts, 0, base, target);
        }
        return closest;
    }
    
    private void dfs(int[] toppingCosts, int index, int currentCost, int target) {
        if (Math.abs(currentCost - target) < Math.abs(closest - target) || 
            (Math.abs(currentCost - target) == Math.abs(closest - target) && currentCost < closest)) {
            closest = currentCost;
        }
        
        if (currentCost >= target || index == toppingCosts.length) {
            return;
        }
        
        dfs(toppingCosts, index + 1, currentCost, target);
        dfs(toppingCosts, index + 1, currentCost + toppingCosts[index], target);
        dfs(toppingCosts, index + 1, currentCost + 2 * toppingCosts[index], target);
    }
}