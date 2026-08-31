class Solution {
    public int evenSumSubgraphs(int[] nums, int[][] edges) {
        int n = nums.length;
        int[] adj = new int[n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj[u] |= (1 << v);
            adj[v] |= (1 << u);
        }

        int count = 0;
        int totalSubsets = 1 << n;

        for (int mask = 1; mask < totalSubsets; mask++) {
            int sum = 0;
            int startNode = -1;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += nums[i];
                    if (startNode == -1) {
                        startNode = i;
                    }
                }
            }

            if (sum % 2 != 0) {
                continue;
            }

            int visited = 0;
            int queue = 1 << startNode;
            visited |= (1 << startNode);

            while (queue != 0) {
                int u = Integer.numberOfTrailingZeros(queue);
                queue &= ~(1 << u);

                int neighbors = adj[u] & mask;
                int unvisitedNeighbors = neighbors & ~visited;

                visited |= unvisitedNeighbors;
                queue |= unvisitedNeighbors;
            }

            if (visited == mask) {
                count++;
            }
        }

        return count;
    }
}