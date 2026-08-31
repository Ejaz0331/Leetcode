class Solution {
    public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        Map<String, List<Pair>> graph1 = buildGraph(pairs1, rates1);
        Map<String, List<Pair>> graph2 = buildGraph(pairs2, rates2);

        Map<String, Double> day1Rates = new HashMap<>();
        dfs(initialCurrency, 1.0, graph1, day1Rates, new HashSet<>());

        double maxAmount = 1.0;

        for (Map.Entry<String, Double> entry : day1Rates.entrySet()) {
            String midCurrency = entry.getKey();
            double day1Value = entry.getValue();

            Map<String, Double> day2Rates = new HashMap<>();
            dfs(midCurrency, day1Value, graph2, day2Rates, new HashSet<>());

            if (day2Rates.containsKey(initialCurrency)) {
                maxAmount = Math.max(maxAmount, day2Rates.get(initialCurrency));
            }
        }

        return maxAmount;
    }

    private Map<String, List<Pair>> buildGraph(List<List<String>> pairs, double[] rates) {
        Map<String, List<Pair>> graph = new HashMap<>();
        for (int i = 0; i < pairs.size(); i++) {
            String u = pairs.get(i).get(0);
            String v = pairs.get(i).get(1);
            double r = rates[i];

            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Pair(v, r));
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Pair(u, 1.0 / r));
        }
        return graph;
    }

    private void dfs(String curr, double currRate, Map<String, List<Pair>> graph, Map<String, Double> visitedRates, Set<String> visited) {
        visitedRates.put(curr, currRate);
        visited.add(curr);

        if (!graph.containsKey(curr)) return;

        for (Pair neighbor : graph.get(curr)) {
            if (!visited.contains(neighbor.to)) {
                dfs(neighbor.to, currRate * neighbor.rate, graph, visitedRates, visited);
            }
        }
    }

    private static class Pair {
        String to;
        double rate;

        Pair(String to, double rate) {
            this.to = to;
            this.rate = rate;
        }
    }
}