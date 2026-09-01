class Solution {
    private java.util.Map<Integer, Boolean> memo;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
        if (sum < desiredTotal) {
            return false;
        }
        if (desiredTotal <= 0) {
            return true;
        }
        
        memo = new java.util.HashMap<>();
        return dfs(maxChoosableInteger, desiredTotal, 0);
    }

    private boolean dfs(int maxChoosableInteger, int desiredTotal, int state) {
        if (memo.containsKey(state)) {
            return memo.get(state);
        }

        for (int i = 1; i <= maxChoosableInteger; i++) {
            int mask = 1 << (i - 1);
            if ((state & mask) == 0) {
                if (i >= desiredTotal || !dfs(maxChoosableInteger, desiredTotal - i, state | mask)) {
                    memo.put(state, true);
                    return true;
                }
            }
        }

        memo.put(state, false);
        return false;
    }
}