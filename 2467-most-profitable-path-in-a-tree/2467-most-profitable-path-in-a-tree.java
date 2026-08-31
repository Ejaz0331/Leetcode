class Solution {
    private List<Integer>[] adj;
    private int[] bobTime;
    private int maxIncome = Integer.MIN_VALUE;

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        bobTime = new int[n];
        Arrays.fill(bobTime, Integer.MAX_VALUE);

        findBobPath(bob, -1, 0);
        dfsAlice(0, -1, 0, 0, amount);

        return maxIncome;
    }

    private boolean findBobPath(int u, int p, int time) {
        bobTime[u] = time;
        if (u == 0) return true;

        for (int v : adj[u]) {
            if (v != p) {
                if (findBobPath(v, u, time + 1)) {
                    return true;
                }
            }
        }

        bobTime[u] = Integer.MAX_VALUE;
        return false;
    }

    private void dfsAlice(int u, int p, int time, int currentIncome, int[] amount) {
        if (time < bobTime[u]) {
            currentIncome += amount[u];
        } else if (time == bobTime[u]) {
            currentIncome += amount[u] / 2;
        }

        boolean isLeaf = true;
        for (int v : adj[u]) {
            if (v != p) {
                isLeaf = false;
                dfsAlice(v, u, time + 1, currentIncome, amount);
            }
        }

        if (isLeaf) {
            maxIncome = Math.max(maxIncome, currentIncome);
        }
    }
}