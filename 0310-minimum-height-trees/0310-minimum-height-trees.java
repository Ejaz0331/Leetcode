class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
          List<Integer> list =new ArrayList<>();
        if(n==1){
            list.add(0);
            return list;
        }
      
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        int degree[]=new int[n];
        Queue<Integer> q=new LinkedList<>();
        for(int arr[]:edges){
            adj.get(arr[0]).add(arr[1]);
            adj.get(arr[1]).add(arr[0]);
            degree[arr[0]]++;
            degree[arr[1]]++;

        }
        for(int i=0;i<n;i++){
            if(degree[i]==1) q.add(i);
        }
        int remaining=n;
        while(remaining>2){
            int size=q.size();
            remaining=remaining-size;
            for(int i=0;i<size;i++){
                int x=q.poll();
                for(int neigh:adj.get(x)){
                    degree[neigh]--;
                    if(degree[neigh]==1)q.add(neigh);
                }
            }
        }
       
 while(!q.isEmpty()) list.add(q.poll());
 return list;
        
    }
}