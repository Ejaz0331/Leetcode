class Solution {
    int minUnfairness = Integer.MAX_VALUE;

    public int distributeCookies(int[] cookies, int k) {
        int[] children = new int[k];
        backtrack(0, cookies, children, k);
        return minUnfairness;
    }

    private void backtrack(int bagIndex, int[] cookies, int[] children, int k) {
        if (bagIndex == cookies.length) {
            int max = 0;
            for (int sum : children) {
                max = Math.max(max, sum);
            }
            minUnfairness = Math.min(minUnfairness, max);
            return;
        }

        for (int i = 0; i < k; i++) {
            if (children[i] + cookies[bagIndex] >= minUnfairness) {
                continue;
            }
            children[i] += cookies[bagIndex];
            backtrack(bagIndex + 1, cookies, children, k);
            children[i] -= cookies[bagIndex];
            
            if (children[i] == 0) {
                break;
            }
        }
    }
}