class Solution {
    public int minMaxWeight(int n, int[][] edges, int threshold) {
        List<int[]>[] reversedAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            reversedAdj[i] = new ArrayList<>();
        }

        int maxWeight = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            reversedAdj[v].add(new int[]{u, w});
            maxWeight = Math.max(maxWeight, w);
        }

        int low = 1, high = maxWeight;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canReachAll(n, reversedAdj, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean canReachAll(int n, List<int[]>[] reversedAdj, int maxAllowedWeight) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int[] next : reversedAdj[u]) {
                int v = next[0];
                int w = next[1];
                if (w <= maxAllowedWeight && !visited[v]) {
                    visited[v] = true;
                    count++;
                    queue.offer(v);
                }
            }
        }

        return count == n;
    }
}