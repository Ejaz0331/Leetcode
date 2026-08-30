class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        int[] parent = new int[n];
        int[] size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int m = accounts.get(i).size();
            for (int j = 1; j < m; j++) {
                String mail = accounts.get(i).get(j);

                if (map.containsKey(mail)) {
                    union(i, map.get(mail), parent, size);
                } else {
                    map.put(mail, i);
                }
            }
        }

        HashMap<Integer, List<String>> mergedMap = new HashMap<>();
        for (String mail : map.keySet()) {
            int index = map.get(mail);
            int ultParent = find(parent, index);

            mergedMap.putIfAbsent(ultParent, new ArrayList<>());
            mergedMap.get(ultParent).add(mail);
        }

        
        List<List<String>> result = new ArrayList<>();
        for (int key : mergedMap.keySet()) {
            List<String> temp = mergedMap.get(key);
            Collections.sort(temp);

            List<String> account = new ArrayList<>();
            account.add(accounts.get(key).get(0)); 
            account.addAll(temp); 
            result.add(account);
        }

        return result;
    }

    private int find(int[] parent, int node) {
        if (parent[node] == node) return node;
        return parent[node] = find(parent, parent[node]); 
    }

    private void union(int u, int v, int[] parent, int[] size) {
        int pu = find(parent, u);
        int pv = find(parent, v);
        if (pu == pv) return;

        if (size[pu] < size[pv]) {
            parent[pu] = pv;
            size[pv] += size[pu];
        } else {
            parent[pv] = pu;
            size[pu] += size[pv];
        }
    }
}