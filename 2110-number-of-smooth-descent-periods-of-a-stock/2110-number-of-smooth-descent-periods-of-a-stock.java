class Solution {
    public long getDescentPeriods(int[] prices) {
        long totalPeriods = 0;
        long currentLength = 0;
        
        for (int i = 0; i < prices.length; i++) {
            if (i > 0 && prices[i] == prices[i - 1] - 1) {
                currentLength++;
            } else {
                currentLength = 1;
            }
            totalPeriods += currentLength;
        }
        
        return totalPeriods;
    }
}