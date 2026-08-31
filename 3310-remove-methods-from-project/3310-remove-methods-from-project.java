class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : invocations) {
            adj[edge[0]].add(edge[1]);
        }

        boolean[] suspicious = new boolean[n];
        dfs(k, adj, suspicious);

        for (int[] edge : invocations) {
            int u = edge[0];
            int v = edge[1];
            if (!suspicious[u] && suspicious[v]) {
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    allMethods.add(i);
                }
                return allMethods;
            }
        }

        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                remaining.add(i);
            }
        }

        return remaining;
    }

    private void dfs(int u, List<Integer>[] adj, boolean[] visited) {
        visited[u] = true;
        for (int v : adj[u]) {
            if (!visited[v]) {
                dfs(v, adj, visited);
            }
        }
    }
}