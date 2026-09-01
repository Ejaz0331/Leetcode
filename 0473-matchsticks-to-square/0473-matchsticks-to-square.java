class Solution {
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks == null || matchsticks.length < 4) {
            return false;
        }
        
        int sum = 0;
        for (int match : matchsticks) {
            sum += match;
        }
        
        if (sum % 4 != 0) {
            return false;
        }
        
        int target = sum / 4;
        
        Integer[] matchsticksBoxed = new Integer[matchsticks.length];
        for (int i = 0; i < matchsticks.length; i++) {
            matchsticksBoxed[i] = matchsticks[i];
        }
        java.util.Arrays.sort(matchsticksBoxed, java.util.Collections.reverseOrder());
        
        int[] sides = new int[4];
        return dfs(matchsticksBoxed, 0, sides, target);
    }
    
    private boolean dfs(Integer[] matchsticks, int index, int[] sides, int target) {
        if (index == matchsticks.length) {
            return sides[0] == target && sides[1] == target && sides[2] == target && sides[3] == target;
        }
        
        int match = matchsticks[index];
        for (int i = 0; i < 4; i++) {
            if (sides[i] + match <= target) {
                sides[i] += match;
                if (dfs(matchsticks, index + 1, sides, target)) {
                    return true;
                }
                sides[i] -= match;
            }
            if (sides[i] == 0) {
                break;
            }
        }
        
        return false;
    }
}