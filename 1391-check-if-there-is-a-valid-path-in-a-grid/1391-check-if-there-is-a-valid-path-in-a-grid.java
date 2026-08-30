class Solution {
    static int rowL;
    static int colL; 
    static int[][] dr={{0,0},{0,0},{-1,1},{0,1},{0,1},{-1,0},{0,-1}};
    static int[][] dc={{0,0},{-1,1},{0,0},{-1,0},{1,0},{0,-1},{1,0}};

    public boolean hasValidPath(int[][] grid) {
        rowL=grid.length;
        colL=grid[0].length;

        boolean[][] vis= new boolean[rowL][colL];

        Queue<int[]> q= new LinkedList<>();
        q.offer(new int[]{0,0});

        vis[0][0]=true;

        while(!q.isEmpty()){
            int[] curr= q.poll();
            int r= curr[0];
            int c= curr[1];

            if(vis[r][c]==true && r==rowL-1 && c==colL-1){
                return true;
            }

            int  move=grid[r][c];
            for(int i=0; i<2; i++){
                int nr= r+dr[move][i];
                int nc= c+dc[move][i];

                if(nr>=0 && nr<rowL && nc>=0 && nc<colL && vis[nr][nc]==false){
                    int nextmove= grid[nr][nc];
                    boolean joins=false;
                    for(int j=0; j<2; j++){
                        if((nr+ dr[nextmove][j]== r) && (nc+ dc[nextmove][j]==c)){
                            joins=true;
                            break;
                        }
                    }

                    if(joins==true){
                        vis[nr][nc]=true;
                        q.offer(new int[] {nr, nc});
                    }   
                }
            }
        }
        return false;
    }
}