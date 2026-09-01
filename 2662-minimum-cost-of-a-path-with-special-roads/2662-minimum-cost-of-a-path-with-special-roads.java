class Solution {
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        List<int[]> roads = new ArrayList<>();
        for (int[] r : specialRoads) {
            if (r[4] < Math.abs(r[2] - r[0]) + Math.abs(r[3] - r[1])) {
                roads.add(r);
            }
        }

        int n = roads.size();
        int ans = Math.abs(target[0] - start[0]) + Math.abs(target[1] - start[1]);
        if (n == 0) {
            return ans;
        }

        int[] dist = new int[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < n; i++) {
            int[] r = roads.get(i);
            dist[i] = Math.abs(r[0] - start[0]) + Math.abs(r[1] - start[1]) + r[4];
            pq.offer(new int[]{dist[i], i});
        }

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];

            if (d > dist[u]) {
                continue;
            }

            int[] rU = roads.get(u);
            ans = Math.min(ans, d + Math.abs(target[0] - rU[2]) + Math.abs(target[1] - rU[3]));

            for (int v = 0; v < n; v++) {
                if (u == v) continue;
                int[] rV = roads.get(v);
                int cost = d + Math.abs(rV[0] - rU[2]) + Math.abs(rV[1] - rU[3]) + rV[4];

                if (cost < dist[v]) {
                    dist[v] = cost;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        return ans;
    }
}