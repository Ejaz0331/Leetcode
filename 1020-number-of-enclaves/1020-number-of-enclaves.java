class Solution {
    public int numEnclaves(int[][] grid) {
        int c=0;
        boolean[][] vis=new boolean[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++){
            if(grid[i][0]==1 && !vis[i][0]) dfs(grid,vis,i,0);
            if(grid[i][grid[0].length-1]==1 && !vis[i][grid[0].length-1]) dfs(grid,vis,i,grid[0].length-1);
        }
        for(int j=0;j<grid[0].length;j++){
            if(grid[0][j]==1 && !vis[0][j]) dfs(grid,vis,0,j);
            if(grid[grid.length-1][j]==1 && !vis[grid.length-1][j]) dfs(grid,vis,grid.length-1,j);
        }
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1 && !vis[i][j]) c++;
            }
        }
        return c;
    }
    public void dfs(int[][] grid,boolean[][] vis,int a,int b){
        if(a<0||a>=grid.length||b<0||b>=grid[0].length||vis[a][b]||grid[a][b]==0) return;
        vis[a][b]=true;
        dfs(grid,vis,a-1,b);
        dfs(grid,vis,a+1,b);
        dfs(grid,vis,a,b-1);
        dfs(grid,vis,a,b+1);
    }
}