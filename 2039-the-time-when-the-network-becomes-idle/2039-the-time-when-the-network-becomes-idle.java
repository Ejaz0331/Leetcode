class Solution {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
   
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        dist[0] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph[u]) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }
            }
        }

       
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int roundTrip = 2 * dist[i];
            int lastSend = ((roundTrip - 1) / patience[i]) * patience[i];
            int idleTime = lastSend + roundTrip;
            ans = Math.max(ans, idleTime);
        }

        return ans + 1;
    }
}