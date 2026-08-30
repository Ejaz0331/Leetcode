class Solution {
    public int[] gardenNoAdj(int n, int[][] paths) {
        ArrayList<ArrayList<Integer>> al =new ArrayList<>();
        for(int i=0;i<n;i++){
            al.add(new ArrayList<>());
        }
        for(int[] path:paths){
            int a = path[0]-1;
            int b =path[1]-1;
            al.get(a).add(b);
            al.get(b).add(a);
        }
        int [] marked = new int[n];
        for(int i=0;i<n;i++){
            int []used = new int[5];
            for(int node:al.get(i)){
                if(marked[node]!=0){
                    used[marked[node]]=1;
                }
            }
            for(int col=1;col<=4;col++){
                if(used[col]==0){
                    marked[i]=col;
                    break;
                }
            }
        }
        return marked;
    }
}