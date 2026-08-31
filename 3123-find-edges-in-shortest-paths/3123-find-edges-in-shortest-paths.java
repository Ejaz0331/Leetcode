import java.util.*;

class Solution {
    public boolean[] findAnswer(int n, int[][] edges) {
        int m = edges.length;
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            adj[u].add(new int[]{v, w, i});
            adj[v].add(new int[]{u, w, i});
        }

        long[] distFrom0 = dijkstra(0, n, adj);
        long[] distFromN = dijkstra(n - 1, n, adj);

        long shortestPath = distFrom0[n - 1];
        boolean[] ans = new boolean[m];

        if (shortestPath == Long.MAX_VALUE) {
            return ans;
        }

        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            long w = edges[i][2];

            if (distFrom0[u] != Long.MAX_VALUE && distFromN[v] != Long.MAX_VALUE) {
                if (distFrom0[u] + w + distFromN[v] == shortestPath) {
                    ans[i] = true;
                    continue;
                }
            }

            if (distFrom0[v] != Long.MAX_VALUE && distFromN[u] != Long.MAX_VALUE) {
                if (distFrom0[v] + w + distFromN[u] == shortestPath) {
                    ans[i] = true;
                }
            }
        }

        return ans;
    }

    private long[] dijkstra(int start, int n, List<int[]>[] adj) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));

        dist[start] = 0;
        pq.offer(new long[]{start, 0});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int u = (int) curr[0];
            long d = curr[1];

            if (d > dist[u]) {
                continue;
            }

            for (int[] edge : adj[u]) {
                int v = edge[0];
                long w = edge[1];

                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new long[]{v, dist[v]});
                }
            }
        }

        return dist;
    }
}
