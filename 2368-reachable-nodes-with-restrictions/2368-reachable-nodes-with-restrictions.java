class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        HashMap<Integer,ArrayList<Integer>> map=new HashMap<>();
        for(int i=0;i<n;i++){
            map.put(i,new ArrayList<>());
        }
        for(int i=0;i<edges.length;i++){
            int v1=edges[i][0];
            int v2=edges[i][1];
            map.get(v1).add(v2);
            map.get(v2).add(v1);
        }
        
        Queue<Integer> q=new LinkedList<>();
        HashSet<Integer> set=new HashSet<>();
        q.add(0);
        HashSet<Integer> restrictedSet = new HashSet<>();
        for(int x : restricted){
            restrictedSet.add(x);
        }
        
        while(!q.isEmpty()){
            int curr=q.poll();
            if(set.contains(curr) || restrictedSet.contains(curr)){
                continue;
            }
            set.add(curr);
            for(int nbrs:map.get(curr)){
                if(!set.contains(nbrs) ){
                   
                    q.add(nbrs);
                    
                }
            }
            

        }
        return set.size();
    }
}