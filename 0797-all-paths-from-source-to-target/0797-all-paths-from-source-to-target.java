class Solution {
    public  List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<Integer> list = new ArrayList<>();
        return helper(graph,0,list);
    }
    public  List<List<Integer>> helper(int[][] graph,int row,List<Integer> list){
        List<List<Integer>> ans = new ArrayList<>();
        if(row == graph.length-1){
            list.add(row);
            ans.add(new ArrayList<>(list));
            list.removeLast();
            return ans;
        }

        for (int i=0; i < graph[row].length;i++){
            list.add(row);
            ans.addAll(helper(graph,graph[row][i],list));
            list.removeLast();
        }
        return ans;
    }
}