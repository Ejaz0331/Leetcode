class Solution {
    int[] p;
    int find(int x) {
        if (p[x] == x) return x;
        return p[x] = find(p[x]);
    }
    void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) p[b] = a;
    }
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        p = new int[n + 1];

        for (int i = 1; i <= n; i++)
            p[i] = i;

        for (int i = threshold + 1; i <= n; i++)
            for (int j = i * 2; j <= n; j += i)
                union(i, j);

        List<Boolean> ans = new ArrayList<>();

        for (int[] q : queries)
            ans.add(find(q[0]) == find(q[1]));

        return ans;
    }
}