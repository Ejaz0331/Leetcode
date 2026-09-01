class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            double p = succProb[i];
            graph.get(u).add(new Pair(v, p));
            graph.get(v).add(new Pair(u, p));
        }

        double[] maxProb = new double[n];
        maxProb[start_node] = 1.0;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Double.compare(b.prob, a.prob));
        pq.offer(new Pair(start_node, 1.0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int node = curr.node;
            double prob = curr.prob;

            if (node == end_node) {
                return prob;
            }

            if (prob < maxProb[node]) {
                continue;
            }

            for (Pair next : graph.get(node)) {
                int nextNode = next.node;
                double nextProb = prob * next.prob;

                if (nextProb > maxProb[nextNode]) {
                    maxProb[nextNode] = nextProb;
                    pq.offer(new Pair(nextNode, nextProb));
                }
            }
        }

        return 0.0;
    }

    private static class Pair {
        int node;
        double prob;

        Pair(int node, double prob) {
            this.node = node;
            this.prob = prob;
        }
    }
}