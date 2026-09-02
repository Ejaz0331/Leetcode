class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        java.util.Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int maxScore = 0;
        int suffixSum = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            suffixSum += satisfaction[i];
            if (suffixSum > 0) {
                maxScore += suffixSum;
            } else {
                break;
            }
        }
        
        return maxScore;
    }
}