class Solution {
    public int magnificentSets(int n, int[][] edges) {
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        int[] color = new int[n + 1];
        List<List<Integer>> components = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                List<Integer> component = new ArrayList<>();
                if (!isBipartite(i, 1, color, adj, component)) {
                    return -1;
                }
                components.add(component);
            }
        }

        
        int[] maxDepth = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            maxDepth[i] = bfs(i, n, adj);
        }

        
        int totalGroups = 0;
        for (List<Integer> component : components) {
            int maxGroupsInComponent = 0;
            for (int node : component) {
                maxGroupsInComponent = Math.max(maxGroupsInComponent, maxDepth[node]);
            }
            totalGroups += maxGroupsInComponent;
        }

        return totalGroups;
    }

    private boolean isBipartite(int node, int c, int[] color, List<Integer>[] adj, List<Integer> component) {
        color[node] = c;
        component.add(node);

        for (int neighbor : adj[node]) {
            if (color[neighbor] == c) {
                return false;
            }
            if (color[neighbor] == 0 && !isBipartite(neighbor, -c, color, adj, component)) {
                return false;
            }
        }
        return true;
    }

    private int bfs(int start, int n, List<Integer>[] adj) {
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        queue.offer(start);
        dist[start] = 1;
        int depth = 1;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adj[node]) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[node] + 1;
                    depth = Math.max(depth, dist[neighbor]);
                    queue.offer(neighbor);
                }
            }
        }

        return depth;
    }
}