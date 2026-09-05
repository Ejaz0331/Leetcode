class Solution {
    public int minOperations(String s1, String s2, int x) {
        int n = s1.length();
        List<Integer> diff = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff.add(i);
            }
        }
        
        int m = diff.size();
        if (m % 2 != 0) {
            return -1;
        }
        
        if (m == 0) {
            return 0;
        }
        
        int[] dp = new int[m + 1];
        dp[0] = 0;
        dp[1] = x;
        
        for (int i = 2; i <= m; i++) {
            int cost1 = dp[i - 1] + x;
            int cost2 = dp[i - 2] + 2 * (diff.get(i - 1) - diff.get(i - 2));
            dp[i] = Math.min(cost1, cost2);
        }
        
        return dp[m] / 2;
    }
}