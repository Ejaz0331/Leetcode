class Solution {
    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        List<Integer>[] hatToPeople = new List[41];
        for (int i = 1; i <= 40; i++) {
            hatToPeople[i] = new java.util.ArrayList<>();
        }
        
        for (int person = 0; person < n; person++) {
            for (int hat : hats.get(person)) {
                hatToPeople[hat].add(person);
            }
        }
        
        int MOD = 1_000_000_007;
        int targetMask = (1 << n) - 1;
        int[][] dp = new int[41][1 << n];
        dp[0][0] = 1;
        
        for (int hat = 1; hat <= 40; hat++) {
            for (int mask = 0; mask <= targetMask; mask++) {
                dp[hat][mask] = dp[hat - 1][mask];
                
                for (int person : hatToPeople[hat]) {
                    if ((mask & (1 << person)) != 0) {
                        int prevMask = mask ^ (1 << person);
                        dp[hat][mask] = (dp[hat][mask] + dp[hat - 1][prevMask]) % MOD;
                    }
                }
            }
        }
        
        return dp[40][targetMask];
    }
}