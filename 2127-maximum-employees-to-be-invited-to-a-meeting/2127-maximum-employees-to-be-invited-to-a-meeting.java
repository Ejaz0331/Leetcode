class Solution {
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] inDegree = new int[n];
        for (int fav : favorite) {
            inDegree[fav]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int[] chainLen = new int[n];
        Arrays.fill(chainLen, 1);
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            int v = favorite[u];
            chainLen[v] = Math.max(chainLen[v], chainLen[u] + 1);
            if (--inDegree[v] == 0) {
                queue.offer(v);
            }
        }
        
        int maxCycleLen = 0;
        int twoCycleChains = 0;
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i] && inDegree[i] > 0) {
                int current = i;
                int count = 0;
                List<Integer> cycleNodes = new ArrayList<>();
                while (!visited[current]) {
                    visited[current] = true;
                    cycleNodes.add(current);
                    current = favorite[current];
                }
                
                int cycleStartIdx = cycleNodes.indexOf(current);
                if (cycleStartIdx != -1) {
                    int cycleLen = cycleNodes.size() - cycleStartIdx;
                    if (cycleLen == 2) {
                        int u = cycleNodes.get(cycleNodes.size() - 1);
                        int v = cycleNodes.get(cycleNodes.size() - 2);
                        twoCycleChains += chainLen[u] + chainLen[v];
                    } else {
                        maxCycleLen = Math.max(maxCycleLen, cycleLen);
                    }
                }
            }
        }
        
        return Math.max(maxCycleLen, twoCycleChains);
    }
}