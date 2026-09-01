class Solution {
    public int minTime(int n, int[][] edges) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int start = edge[2];
            int end = edge[3];
            graph[u].add(new int[]{v, start, end});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0];
            int u = curr[1];

            if (u == n - 1) {
                return time;
            }

            if (time > dist[u]) {
                continue;
            }

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int start = edge[1];
                int end = edge[2];

                if (time <= end) {
                    int departTime = Math.max(time, start);
                    int arrivalTime = departTime + 1;

                    if (arrivalTime < dist[v]) {
                        dist[v] = arrivalTime;
                        pq.offer(new int[]{arrivalTime, v});
                    }
                }
            }
        }

        return -1;
    }
}