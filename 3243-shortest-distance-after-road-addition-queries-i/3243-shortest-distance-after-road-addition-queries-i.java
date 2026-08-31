import java.util.*;

class Solution {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            adj[i].add(i + 1);
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            adj[u].add(v);

            answer[i] = bfs(n, adj);
        }

        return answer;
    }

    private int bfs(int n, List<Integer>[] adj) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        dist[0] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (curr == n - 1) {
                return dist[curr];
            }

            for (int next : adj[curr]) {
                if (dist[next] == -1) {
                    dist[next] = dist[curr] + 1;
                    queue.offer(next);
                }
            }
        }

        return dist[n - 1];
    }
}