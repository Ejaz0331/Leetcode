class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;

        List<Integer>[] adj1 = buildAdj(n, edges1);
        List<Integer>[] adj2 = buildAdj(m, edges2);

        int maxTree2 = 0;
        if (k > 0) {
            for (int j = 0; j < m; j++) {
                maxTree2 = Math.max(maxTree2, countNodesWithinK(j, -1, 0, k - 1, adj2));
            }
        }

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int countTree1 = countNodesWithinK(i, -1, 0, k, adj1);
            answer[i] = countTree1 + maxTree2;
        }

        return answer;
    }

    private List<Integer>[] buildAdj(int size, int[][] edges) {
        List<Integer>[] adj = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        return adj;
    }

    private int countNodesWithinK(int u, int p, int depth, int maxDist, List<Integer>[] adj) {
        if (depth > maxDist) return 0;
        int count = 1;
        for (int v : adj[u]) {
            if (v != p) {
                count += countNodesWithinK(v, u, depth + 1, maxDist, adj);
            }
        }
        return count;
    }
}