class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                tree[manager[i]].add(i);
            }
        }

        return dfs(headID, tree, informTime);
    }

    private int dfs(int node, List<Integer>[] tree, int[] informTime) {

        int maxTime = 0;

        for (int child : tree[node]) {
            maxTime = Math.max(maxTime, dfs(child, tree, informTime));
        }

        return informTime[node] + maxTime;
    }
}