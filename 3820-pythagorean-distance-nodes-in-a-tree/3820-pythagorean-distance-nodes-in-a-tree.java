class Solution {
    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        int[] distX = getDistances(n, adj, x);
        int[] distY = getDistances(n, adj, y);
        int[] distZ = getDistances(n, adj, z);

        int count = 0;
        for (int i = 0; i < n; i++) {
            long[] d = {distX[i], distY[i], distZ[i]};
            Arrays.sort(d);

            if (d[0] * d[0] + d[1] * d[1] == d[2] * d[2]) {
                count++;
            }
        }

        return count;
    }

    private int[] getDistances(int n, List<Integer>[] adj, int start) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : adj[u]) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    queue.offer(v);
                }
            }
        }

        return dist;
    }
}