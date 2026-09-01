class Solution {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int time = curr[1];

            if (time > dist[u]) {
                continue;
            }

            for (int[] next : graph.get(u)) {
                int v = next[0];
                int nextTime = time + next[1];

                if (nextTime < disappear[v] && (dist[v] == -1 || nextTime < dist[v])) {
                    dist[v] = nextTime;
                    pq.offer(new int[]{v, nextTime});
                }
            }
        }

        return dist;
    }
}