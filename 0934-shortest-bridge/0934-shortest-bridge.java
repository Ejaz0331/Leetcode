class Solution {

    int rows,cols;
    final int val = 2;
    public boolean validateCoOrdinates(int currRow,int currCol,boolean isVisited[][]){
        return (currRow<rows && currRow>=0 && currCol<cols && currCol>=0 && !isVisited[currRow][currCol]);
    }

    public Pair findCell(int arr[][],int toFind){
        Pair toAdd=null;
        boolean stop = false;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(arr[i][j]==1) {
                    toAdd = new Pair(i,j);
                    stop=true;
                    break;
                }
            }
            if(stop) break;
        }
        return toAdd;
    }

    public List<Pair> findCells(int arr[][],int toFind){
        List<Pair> nodes = new ArrayList<>();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(arr[i][j]==1) {
                    nodes.add(new Pair(i,j));
                }
            }
        }
        return nodes;
    }

    public void bfs(int arr[][]){
        Queue<Pair> q = new LinkedList<>();
        boolean isVisited[][] = new boolean[rows][cols],stop = false;
        Pair [] directions = {new Pair(1,0),new Pair(-1,0),new Pair(0,1),new Pair(0,-1)};
        Pair toAdd = findCell(arr,1);
        q.add(toAdd);
        isVisited[toAdd.row][toAdd.col] = true;
        arr[toAdd.row][toAdd.col] = val;
        while(!q.isEmpty()){
            Pair frontNode = q.poll();
            int currRow = frontNode.row;
            int currCol = frontNode.col;

            for(Pair direction:directions){
                int newRow = currRow + direction.row;
                int newCol = currCol + direction.col;
                boolean isValid = validateCoOrdinates(newRow,newCol,isVisited);

                if(isValid && arr[newRow][newCol]==1){
                    q.add(new Pair(newRow,newCol));
                    isVisited[newRow][newCol] = true;
                    arr[newRow][newCol] = val;
                }
            }

        }

    }

    public int multiSourceBfs(int arr[][]){
        Queue<Pair> q = new LinkedList<>();
        boolean isVisited[][] = new boolean[rows][cols];
        Pair [] directions = {new Pair(1,0),new Pair(-1,0),new Pair(0,1),new Pair(0,-1)};
        List<Pair> nodes = findCells(arr,1);
        for(Pair toAdd : nodes){
            q.add(toAdd);
            isVisited[toAdd.row][toAdd.col] = true;
        }
        int steps = 0;
        while(!q.isEmpty()){
            int qSize = q.size();
            for(int k=1;k<=qSize;k++){
                Pair frontNode = q.poll();
                int currRow = frontNode.row;
                int currCol = frontNode.col;
                if(arr[currRow][currCol]==val) return steps-1;
                for(Pair direction:directions){
                    int newRow = currRow + direction.row;
                    int newCol = currCol + direction.col;
                    boolean isValid = validateCoOrdinates(newRow,newCol,isVisited);

                    if(isValid ){
                        q.add(new Pair(newRow,newCol));
                        isVisited[newRow][newCol] = true;
                    }
                }
            }
            steps++;
        }

        return -1;
    }

    public int shortestBridge(int[][] arr) {
        // start bfs on any '1' , we'll cover one island
        // while covering , make grid[i][j]=2
        // now run multisource bfs on remaining ones , we'll find the shorted path to nearest 0
        // ans = steps-1
        this.rows = arr.length;
        this.cols = arr[0].length;
        bfs(arr);
        return multiSourceBfs(arr);
    }
}

class Pair {
    int row;
    int col;
    Pair(int row,int col){
        this.row = row;
        this.col = col;
    }
    public String toString(){
        return "row : "+this.row+" , col : "+this.col;
    }
}