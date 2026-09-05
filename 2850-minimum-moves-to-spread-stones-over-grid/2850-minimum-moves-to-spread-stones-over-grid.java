class Solution {
    public int minimumMoves(int[][] grid) {
        List<int[]> excess = new ArrayList<>();
        List<int[]> empty = new ArrayList<>();

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (grid[r][c] > 1) {
                    for (int k = 0; k < grid[r][c] - 1; k++) {
                        excess.add(new int[]{r, c});
                    }
                } else if (grid[r][c] == 0) {
                    empty.add(new int[]{r, c});
                }
            }
        }

        return solve(0, excess, empty);
    }

    private int solve(int idx, List<int[]> excess, List<int[]> empty) {
        if (idx == excess.size()) {
            return 0;
        }

        int minMoves = Integer.MAX_VALUE;
        int[] src = excess.get(idx);

        for (int i = 0; i < empty.size(); i++) {
            int[] dst = empty.get(i);
            int dist = Math.abs(src[0] - dst[0]) + Math.abs(src[1] - dst[1]);
            
            int[] removed = empty.remove(i);
            minMoves = Math.min(minMoves, dist + solve(idx + 1, excess, empty));
            empty.add(i, removed);
        }

        return minMoves;
    }
}