class Solution {
    private long totalFuel = 0;

    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length + 1;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            adj[road[0]].add(road[1]);
            adj[road[1]].add(road[0]);
        }

        dfs(0, -1, adj, seats);
        return totalFuel;
    }

    private long dfs(int node, int parent, List<Integer>[] adj, int seats) {
        long representatives = 1;

        for (int neighbor : adj[node]) {
            if (neighbor != parent) {
                representatives += dfs(neighbor, node, adj, seats);
            }
        }

        if (node != 0) {
            totalFuel += (representatives + seats - 1) / seats;
        }

        return representatives;
    }
}