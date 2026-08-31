import java.util.*;

class Solution {
    public int minimumThreshold(int n, int[][] edges, int source, int target, int k) {
        if (source == target) {
            return 0;
        }

        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        int[] weights = new int[edges.length + 1];
        weights[0] = 0;
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
            weights[i + 1] = w;
        }

        Arrays.sort(weights);

        int low = 0;
        int high = weights.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int threshold = weights[mid];

            if (canReach(n, adj, source, target, threshold, k)) {
                ans = threshold;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean canReach(int n, List<int[]>[] adj, int source, int target, int threshold, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Deque<Integer> deque = new ArrayDeque<>();
        dist[source] = 0;
        deque.offerFirst(source);

        while (!deque.isEmpty()) {
            int u = deque.pollFirst();

            if (u == target) {
                return dist[u] <= k;
            }

            for (int[] edge : adj[u]) {
                int v = edge[0];
                int w = edge[1];
                int cost = (w > threshold) ? 1 : 0;

                if (dist[u] + cost < dist[v]) {
                    dist[v] = dist[u] + cost;
                    if (cost == 0) {
                        deque.offerFirst(v);
                    } else {
                        deque.offerLast(v);
                    }
                }
            }
        }

        return dist[target] <= k;
    }
}