class Solution {
    private static final int MOD = 1_000_000_007;

    public int countRestrictedPaths(int n, int[][] edges) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[]{n, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int d = curr[1];

            if (d > dist[u]) {
                continue;
            }

            for (int[] next : graph.get(u)) {
                int v = next[0];
                int weight = next[1];

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        return dfs(1, n, graph, dist, memo);
    }

    private int dfs(int u, int n, List<List<int[]>> graph, int[] dist, int[] memo) {
        if (u == n) {
            return 1;
        }

        if (memo[u] != -1) {
            return memo[u];
        }

        int ways = 0;
        for (int[] next : graph.get(u)) {
            int v = next[0];
            if (dist[u] > dist[v]) {
                ways = (ways + dfs(v, n, graph, dist, memo)) % MOD;
            }
        }

        return memo[u] = ways;
    }
}