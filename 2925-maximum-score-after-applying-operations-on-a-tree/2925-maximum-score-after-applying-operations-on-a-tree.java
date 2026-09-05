class Solution {
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int n = values.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        long totalSum = 0;
        for (int val : values) {
            totalSum += val;
        }

        long minLost = dfs(0, -1, adj, values);
        return totalSum - minLost;
    }

    private long dfs(int u, int p, List<List<Integer>> adj, int[] values) {
        boolean isLeaf = true;
        long sumSubtreeLose = 0;

        for (int v : adj.get(u)) {
            if (v != p) {
                isLeaf = false;
                sumSubtreeLose += dfs(v, u, adj, values);
            }
        }

        if (isLeaf) {
            return values[u];
        }

        return Math.min((long) values[u], sumSubtreeLose);
    }
}