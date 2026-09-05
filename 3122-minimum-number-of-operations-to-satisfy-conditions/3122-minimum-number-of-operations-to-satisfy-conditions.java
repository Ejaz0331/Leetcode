class Solution {
    private PriorityQueue<List<Integer>> dfs(int[][] grid, int j) {
        if(j == grid[0].length-1) {
            PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a,b) -> Integer.compare(a.get(0), b.get(0)));
            for(int value = 0 ; value <= 9 ; value++) {
                int count = 0;
                for(int i = 0 ; i < grid.length ; i++) {
                    if(grid[i][j] != value) count++;
                }
                queue.add(new ArrayList<>(Arrays.asList(count, value)));
            }
            return queue;
        }
        PriorityQueue<List<Integer>> next = dfs(grid, j+1);
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a,b) -> Integer.compare(a.get(0), b.get(0)));
        for(int value = 0 ; value <= 9 ; value++) {
            int count = 0;
            for(int i = 0 ; i < grid.length ; i++) {
                if(grid[i][j] != value) count++;
            }
            List<Integer> arr = next.poll();
            if(arr.get(1) == value) {
                count += next.peek().get(0);
            } else {
                count += arr.get(0);
            }
            next.add(arr);
            queue.add(new ArrayList<>(Arrays.asList(count, value)));
        }
        return queue;
    }
    public int minimumOperations(int[][] grid) {
        PriorityQueue<List<Integer>> queue = dfs(grid, 0);
        return queue.peek().get(0);
    }
}