class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int targetMask = (1 << n) - 1;
        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][1 << n];
        
        for (int i = 0; i < n; i++) {
            queue.offer(new int[] {i, 1 << i, 0});
            visited[i][1 << i] = true;
        }
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int u = curr[0];
            int mask = curr[1];
            int dist = curr[2];
            
            if (mask == targetMask) {
                return dist;
            }
            
            for (int v : graph[u]) {
                int nextMask = mask | (1 << v);
                if (!visited[v][nextMask]) {
                    visited[v][nextMask] = true;
                    queue.offer(new int[] {v, nextMask, dist + 1});
                }
            }
        }
        
        return 0;
    }
}