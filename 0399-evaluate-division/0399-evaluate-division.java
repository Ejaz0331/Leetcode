class Solution {
    HashMap<String,HashMap<String,Double>> graph = new HashMap<>();
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0); 
            String b = equations.get(i).get(1);   
            Double val = values[i];
            graph.putIfAbsent(a, new HashMap<>());
            graph.putIfAbsent(b, new HashMap<>());

            graph.get(a).put(b,val);
            graph.get(b).put(a,1 / val);
        }
        double[] results = new double[queries.size()];
        for (int i = 0; i<queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            HashSet<String> visited = new HashSet<>();  
            results[i] = dfs(start, end, visited, 1.0);
        }
        return results;
     
        }
    private double dfs(String start, String end, HashSet<String> visited, double accProduct) 
        {
            if (!graph.containsKey(start) || !graph.containsKey(end)) return -1.0; 
            if (start.equals(end)) return accProduct;
            visited.add(start);
           
            for (Map.Entry<String, Double> neighbor : graph.get(start).entrySet()) {
                if (!visited.contains(neighbor.getKey())) {
                    double result = dfs(neighbor.getKey(), end, visited, accProduct * neighbor.getValue());
                    if (result != -1.0) return result; 

                }
            }
            return -1.0;

        }
}