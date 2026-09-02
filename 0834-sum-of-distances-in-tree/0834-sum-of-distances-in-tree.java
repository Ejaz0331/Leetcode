class Solution {
    private List<List<Integer>> graph;
    private int[] count;
    private int[] res;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        count = new int[n];
        res = new int[n];
        
        dfs(0, -1, 0);
        dfs2(0, -1, n);
        
        return res;
    }
    
    private void dfs(int node, int parent, int depth) {
        count[node] = 1;
        res[0] += depth;
        for (int neighbor : graph.get(node)) {
            if (neighbor != parent) {
                dfs(neighbor, node, depth + 1);
                count[node] += count[neighbor];
            }
        }
    }
    
    private void dfs2(int node, int parent, int n) {
        for (int neighbor : graph.get(node)) {
            if (neighbor != parent) {
                res[neighbor] = res[node] - count[neighbor] + (n - count[neighbor]);
                dfs2(neighbor, node, n);
            }
        }
    }
}