class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;

        List<Integer>[] adj1 = buildAdj(n, edges1);
        List<Integer>[] adj2 = buildAdj(m, edges2);

        int[] color1 = new int[n];
        int[] count1 = new int[2];
        dfsColor(0, -1, 0, adj1, color1, count1);

        int[] color2 = new int[m];
        int[] count2 = new int[2];
        dfsColor(0, -1, 0, adj2, color2, count2);

        int maxTree2 = Math.max(count2[0], count2[1]);

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = count1[color1[i]] + maxTree2;
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

    private void dfsColor(int u, int p, int c, List<Integer>[] adj, int[] color, int[] count) {
        color[u] = c;
        count[c]++;
        for (int v : adj[u]) {
            if (v != p) {
                dfsColor(v, u, 1 - c, adj, color, count);
            }
        }
    }
}