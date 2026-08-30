class Solution {
    class DisjointSet{
        List<Integer> size = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();
        DisjointSet(int n){
            for(int i=0; i<n; i++){
                size.add(1);
                parent.add(i);
            }
        }
    public int findUP(int node){
        if(node == parent.get(node)) return node;
        //path compression imp optimization here
        int ultiParent = findUP(parent.get(node));
        parent.set(node, ultiParent);
        return ultiParent;
    }
    public void unionBySize(int u, int v){
        int ult_u = findUP(u);
        int ult_v = findUP(v);
        if(ult_u == ult_v) return;

        if(size.get(ult_u) < size.get(ult_v)){
            parent.set(ult_u, ult_v);
            size.set(ult_v, size.get(ult_v) + size.get(ult_u)); 
        }
        else{
            parent.set(ult_v, ult_u); 
            size.set(ult_u, size.get(ult_u) + size.get(ult_v));
        }
    }
}
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length; int[] res = new int[2];
        DisjointSet ds = new DisjointSet(n+1);
        for(int[] temp: edges){
            int fir = temp[0];
            int sec = temp[1];
            if(ds.findUP(fir) == ds.findUP(sec)){
                res = temp;
            }
            else{
                ds.unionBySize(fir, sec);
            }
        }
        return res;
    }
}