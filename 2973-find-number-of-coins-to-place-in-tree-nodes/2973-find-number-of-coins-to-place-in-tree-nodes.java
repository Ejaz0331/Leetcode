class Solution {
    private List<Integer>[] adj;
    private long[] ans;
    private int[] cost;

    public long[] placedCoins(int[][] edges, int[] cost) {
        int n = cost.length;
        this.cost = cost;
        this.ans = new long[n];
        this.adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        dfs(0, -1);
        return ans;
    }

    private List<Integer> dfs(int u, int p) {
        List<Integer> list = new ArrayList<>();
        list.add(cost[u]);

        for (int v : adj[u]) {
            if (v != p) {
                list.addAll(dfs(v, u));
            }
        }

        Collections.sort(list);
        int m = list.size();

        if (m < 3) {
            ans[u] = 1;
        } else {
            long p1 = (long) list.get(m - 1) * list.get(m - 2) * list.get(m - 3);
            long p2 = (long) list.get(0) * list.get(1) * list.get(m - 1);
            long maxProd = Math.max(p1, p2);
            ans[u] = Math.max(0, maxProd);
        }

        if (m <= 5) {
            return list;
        }

        List<Integer> res = new ArrayList<>();
        res.add(list.get(0));
        res.add(list.get(1));
        res.add(list.get(m - 3));
        res.add(list.get(m - 2));
        res.add(list.get(m - 1));
        return res;
    }
}