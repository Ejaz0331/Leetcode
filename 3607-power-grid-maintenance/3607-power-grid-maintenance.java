import java.util.*;

class Solution {
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        List<Integer>[] adj = new ArrayList[c + 1];
        for (int i = 1; i <= c; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] conn : connections) {
            adj[conn[0]].add(conn[1]);
            adj[conn[1]].add(conn[0]);
        }

        int[] compId = new int[c + 1];
        List<TreeSet<Integer>> compSets = new ArrayList<>();
        int compCount = 0;

        for (int i = 1; i <= c; i++) {
            if (compId[i] == 0) {
                compCount++;
                TreeSet<Integer> set = new TreeSet<>();
                dfs(i, adj, compId, compCount, set);
                compSets.add(set);
            }
        }

        boolean[] isOnline = new boolean[c + 1];
        Arrays.fill(isOnline, true);

        List<Integer> resultList = new ArrayList<>();

        for (int[] query : queries) {
            int type = query[0];
            int x = query[1];

            if (type == 1) {
                if (isOnline[x]) {
                    resultList.add(x);
                } else {
                    int cid = compId[x] - 1;
                    TreeSet<Integer> set = compSets.get(cid);
                    if (set.isEmpty()) {
                        resultList.add(-1);
                    } else {
                        resultList.add(set.first());
                    }
                }
            } else if (type == 2) {
                if (isOnline[x]) {
                    isOnline[x] = false;
                    int cid = compId[x] - 1;
                    compSets.get(cid).remove(x);
                }
            }
        }

        int[] ans = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            ans[i] = resultList.get(i);
        }

        return ans;
    }

    private void dfs(int u, List<Integer>[] adj, int[] compId, int currentComp, TreeSet<Integer> set) {
        compId[u] = currentComp;
        set.add(u);

        for (int v : adj[u]) {
            if (compId[v] == 0) {
                dfs(v, adj, compId, currentComp, set);
            }
        }
    }
}