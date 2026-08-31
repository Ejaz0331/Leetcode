class Solution {
    public int[] baseUnitConversions(int[][] conversions) {
        int n = conversions.length + 1;
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] conv : conversions) {
            int u = conv[0];
            int v = conv[1];
            int factor = conv[2];
            adj[u].add(new int[]{v, factor});
        }

        int[] result = new int[n];
        int mod = 1_000_000_007;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        result[0] = 1;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int[] next : adj[u]) {
                int v = next[0];
                int factor = next[1];

                result[v] = (int) (((long) result[u] * factor) % mod);
                queue.offer(v);
            }
        }

        return result;
    }
}