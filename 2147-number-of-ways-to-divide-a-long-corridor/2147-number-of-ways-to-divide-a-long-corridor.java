class Solution {
    public int numberOfWays(String corridor) {
        long ways = 1;
        int seats = 0;
        int plants = 0;
        int mod = 1_000_000_007;
        
        for (char c : corridor.toCharArray()) {
            if (c == 'S') {
                seats++;
                if (seats > 2 && seats % 2 == 1) {
                    ways = (ways * (plants + 1)) % mod;
                    plants = 0;
                }
            } else {
                if (seats >= 2 && seats % 2 == 0) {
                    plants++;
                }
            }
        }
        
        if (seats == 0 || seats % 2 != 0) {
            return 0;
        }
        
        return (int) ways;
    }
}