class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        int n = graph.length;
        int []vis = new int[n];
        for(int i=0;i<n;i++){
            if(dfs(i,graph,vis)){
                res.add(i);
            }
        }
        return res;
    }
    boolean dfs(int node,int[][] graph, int []vis){
        if(vis[node]==2){
            return true;
        }
        if(vis[node]==1){
            return false;
        }
        vis[node] = 1;
        for(int nbrs:graph[node]){
            if(!dfs(nbrs,graph,vis)){
                return false;
            }
        }
        vis[node] = 2;
        return true;
    }
}