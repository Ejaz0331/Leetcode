class Solution {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        int[] ans = new int[n];
        dfs(0, -1, adj, labels, ans);
        return ans;
    }
    private int[] dfs(int node, int parent, List<List<Integer>> adj, String labels, int[] ans) {
        int[] freq = new int[26]; 
        freq[labels.charAt(node) - 'a']++; 
        for (int child : adj.get(node)) {
            if (child == parent) continue; 
            int[] childFreq = dfs(child, node, adj, labels, ans);
            for (int i = 0; i < 26; i++) freq[i] += childFreq[i];
        }
        ans[node] = freq[labels.charAt(node) - 'a']; 
        return freq;
    }
}