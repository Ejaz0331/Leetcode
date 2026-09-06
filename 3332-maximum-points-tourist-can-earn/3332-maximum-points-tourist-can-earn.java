class Solution {
    public int maxScore(int n, int k, int[][] stayScore, int[][] travelScore) {
        int[] dp = new int[n];

        for (int day = 0; day < k; day++) {
            int[] nextDp = new int[n];
            for (int curr = 0; curr < n; curr++) {
                int stayOption = dp[curr] + stayScore[day][curr];
                int maxTravel = dp[curr];
                for (int dest = 0; dest < n; dest++) {
                    if (curr != dest) {
                        maxTravel = Math.max(maxTravel, dp[dest] + travelScore[dest][curr]);
                    }
                }
                nextDp[curr] = Math.max(stayOption, maxTravel);
            }
            dp = nextDp;
        }

        int maxPoints = 0;
        for (int i = 0; i < n; i++) {
            maxPoints = Math.max(maxPoints, dp[i]);
        }

        return maxPoints;
    }
}