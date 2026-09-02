class Solution {
    public String shortestSuperstring(String[] words) {
        int n = words.length;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                graph[i][j] = words[j].length();
                for (int k = 1; k <= Math.min(words[i].length(), words[j].length()); k++) {
                    if (words[i].substring(words[i].length() - k).equals(words[j].substring(0, k))) {
                        graph[i][j] = words[j].length() - k;
                    }
                }
            }
        }
        
        int[][] dp = new int[1 << n][n];
        int[][] parent = new int[1 << n][n];
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                parent[i][j] = -1;
            }
        }
        
        for (int mask = 1; mask < (1 << n); mask++) {
            for (int bit = 0; bit < n; bit++) {
                if ((mask & (1 << bit)) == 0) continue;
                int prevMask = mask ^ (1 << bit);
                if (prevMask == 0) {
                    dp[mask][bit] = words[bit].length();
                    continue;
                }
                dp[mask][bit] = 1000000;
                for (int i = 0; i < n; i++) {
                    if ((prevMask & (1 << i)) == 0) continue;
                    int val = dp[prevMask][i] + graph[i][bit];
                    if (val < dp[mask][bit]) {
                        dp[mask][bit] = val;
                        parent[mask][bit] = i;
                    }
                }
            }
        }
        
        int minLen = 1000000;
        int lastNode = -1;
        int fullMask = (1 << n) - 1;
        for (int i = 0; i < n; i++) {
            if (dp[fullMask][i] < minLen) {
                minLen = dp[fullMask][i];
                lastNode = i;
            }
        }
        
        int[] path = new int[n];
        int pIndex = n - 1;
        int currMask = fullMask;
        int currNode = lastNode;
        while (currNode != -1) {
            path[pIndex--] = currNode;
            int nextNode = parent[currMask][currNode];
            currMask ^= (1 << currNode);
            currNode = nextNode;
        }
        
        StringBuilder sb = new StringBuilder(words[path[pIndex + 1]]);
        for (int i = pIndex + 2; i < n; i++) {
            int u = path[i - 1];
            int v = path[i];
            int overlap = words[v].length() - graph[u][v];
            sb.append(words[v].substring(overlap));
        }
        
        return sb.toString();
    }
}