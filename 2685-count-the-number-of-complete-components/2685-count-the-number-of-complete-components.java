class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] counts = new int[2];
                dfs(i, adj, visited, counts);

                int nodes = counts[0];
                int totalDegree = counts[1];

                if (totalDegree == nodes * (nodes - 1)) {
                    completeCount++;
                }
            }
        }

        return completeCount;
    }

    private void dfs(int u, List<Integer>[] adj, boolean[] visited, int[] counts) {
        visited[u] = true;
        counts[0]++;
        counts[1] += adj[u].size();

        for (int v : adj[u]) {
            if (!visited[v]) {
                dfs(v, adj, visited, counts);
            }
        }
    }
}