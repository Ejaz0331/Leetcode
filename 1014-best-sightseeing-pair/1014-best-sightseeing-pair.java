class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int maxScore = 0;
        int maxLeft = values[0];
        
        for (int j = 1; j < values.length; j++) {
            maxScore = Math.max(maxScore, maxLeft + values[j] - j);
            maxLeft = Math.max(maxLeft, values[j] + j);
        }
        
        return maxScore;
    }
}