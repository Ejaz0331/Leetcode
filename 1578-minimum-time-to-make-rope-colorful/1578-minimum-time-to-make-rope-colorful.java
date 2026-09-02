class Solution {
    public int minCost(String colors, int[] neededTime) {
        int totalCost = 0;
        int n = colors.length();
        
        for (int i = 0; i < n; ) {
            char currentColor = colors.charAt(i);
            int groupSum = 0;
            int groupMax = 0;
            int j = i;
            
            while (j < n && colors.charAt(j) == currentColor) {
                groupSum += neededTime[j];
                groupMax = Math.max(groupMax, neededTime[j]);
                j++;
            }
            
            if (j - i > 1) {
                totalCost += (groupSum - groupMax);
            }
            
            i = j;
        }
        
        return totalCost;
    }
}