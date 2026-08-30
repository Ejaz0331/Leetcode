class Solution {
    boolean b;
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int c=0;
        for(int i=0;i<grid1.length;i++){
            for(int j=0;j<grid1[0].length;j++){
                if(grid2[i][j]==1){
                    b=true;
                    dfs(grid1,grid2,i,j);
                    if(b) c++;
                }
            }
        }
        return c;
    }
    public void dfs(int[][] grid1,int[][] grid2,int i,int j){
        if(i<0||j<0||i>=grid1.length||j>=grid1[0].length||grid2[i][j]==0) return;
        if(grid1[i][j]!=grid2[i][j]) b=false;
        grid2[i][j]=0;
        dfs(grid1,grid2,i-1,j);
        dfs(grid1,grid2,i+1,j);
        dfs(grid1,grid2,i,j-1);
        dfs(grid1,grid2,i,j+1);
    }
}