class Solution {
    public int numberOfComponents(int[][] properties, int k) {
        int n = properties.length;
        List<Set<Integer>> sets = new ArrayList<>();
        for (int[] prop : properties) {
            Set<Integer> set = new HashSet<>();
            for (int val : prop) {
                set.add(val);
            }
            sets.add(set);
        }

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int components = n;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int common = 0;
                for (int val : sets.get(i)) {
                    if (sets.get(j).contains(val)) {
                        common++;
                    }
                }

                if (common >= k) {
                    int rootI = find(parent, i);
                    int rootJ = find(parent, j);
                    if (rootI != rootJ) {
                        parent[rootI] = rootJ;
                        components--;
                    }
                }
            }
        }

        return components;
    }

    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent, parent[i]);
    }
}