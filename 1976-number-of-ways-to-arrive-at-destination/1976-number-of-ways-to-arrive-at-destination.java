class Solution {
    public int countPaths(int n, int[][] roads) {
        final int MOD = 1_000_000_007;
      
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int time = road[2];

            graph.get(u).add(new int[]{v, time});
            graph.get(v).add(new int[]{u, time});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        int[] ways = new int[n];

        PriorityQueue<long[]> pq =
                new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        dist[0] = 0;
        ways[0] = 1;
       
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();

            long currTime = curr[0];
            int node = (int) curr[1];
         
            if (currTime > dist[node]) {
                continue;
            }

            for (int[] edge : graph.get(node)) {
                int nbr = edge[0];
                int edgeTime = edge[1];

                if (currTime + edgeTime < dist[nbr]) {
                    dist[nbr] = currTime + edgeTime;
                    ways[nbr] = ways[node];
                    pq.offer(new long[]{dist[nbr], nbr});
                } 

                else if (currTime + edgeTime == dist[nbr]) {
                    ways[nbr] = (ways[nbr] + ways[node]) % MOD;
                }
            }
        }

        return ways[n - 1];
    }
}