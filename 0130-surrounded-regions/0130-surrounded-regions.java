class Solution {
    public void solve(char[][] board) {
        boolean vis[][] = new boolean [board.length][board[0].length];
       
       Queue<int[]> qu = new LinkedList<>();
       
       for(int i =0;i< board.length;i++){
           for(int j =0;j< board[0].length;j++){
               if(i==0||j==0||i==board.length-1||j==board[0].length-1){
                   if(board[i][j]=='O'){
                       vis[i][j]=true;
                       qu.add(new int[]{i,j});
                   }
               }
           }
       }
       while(!qu.isEmpty()){
           int arr[]= qu.poll();
           int r = arr[0];
           int c =arr[1];
         
           if(r-1>=0&&!vis[r-1][c]&&board[r-1][c]=='O'){
               vis[r-1][c]=true;
               qu.add(new int []{r-1,c});
           }
           if(c-1>=0&&!vis[r][c-1]&&board[r][c-1]=='O'){
               vis[r][c-1]=true;
               qu.add(new int []{r,c-1});
           }
           
           if(r+1<board.length&&!vis[r+1][c]&&board[r+1][c]=='O'){
               vis[r+1][c]=true;
               qu.add(new int []{r+1,c});
           }

           if(c+1<board[0].length&&!vis[r][c+1]&&board[r][c+1]=='O'){
               vis[r][c+1]=true;
               qu.add(new int []{r,c+1});
           }           
       }
       for(int i =0;i<board.length;i++){
           for(int j = 0 ;j< board[0].length;j++){
               if(!vis[i][j]){
                   board[i][j]='X';
               }else{
                   board[i][j]='O';
               }
           }
       }
        
    }
}