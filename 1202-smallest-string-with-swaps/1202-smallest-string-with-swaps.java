class Solution {
    int[] rank;
    int[] parent;
    int findParent(int node){
        if(node==parent[node]){
            return node;
        }
        return parent[node]=findParent(parent[node]);
    }
    void union(int u,int v){
        int p_u=findParent(u);
        int p_v=findParent(v);
        if(p_u==p_v) return;
        if(rank[p_u]==rank[p_v]){
            parent[p_v]=p_u;
            rank[p_u]++;
        }
        else if(rank[p_u]<rank[p_v]){
            parent[p_u]=p_v;
        }
        else{
            parent[p_v]=p_u;
        }
    }
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n=s.length();
        rank=new int[n];
        parent=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
        for(List<Integer> ls:pairs){
            union(ls.get(0),ls.get(1));
        }
        Map<Integer,PriorityQueue<Character>> map=new HashMap<>();
        for(int i=0;i<n;i++){
            int p=findParent(i);
            map.putIfAbsent(p,new PriorityQueue<>());
            PriorityQueue<Character> pq=map.get(p);
            pq.add(s.charAt(i));
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;i++){
            int p=findParent(i);
            PriorityQueue<Character> pq=map.get(p);
            sb.append(pq.poll());
            
        }
        return sb.toString();
    }
}