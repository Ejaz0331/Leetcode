class Solution {
    private static final boolean[] isPrime = new boolean[100000];

    static {
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int p = 2; p * p < 100000; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i < 100000; i += p) {
                    isPrime[i] = false;
                }
            }
        }
    }

    public int minOperations(int n, int m) {
        if (isPrime[n] || isPrime[m]) {
            return -1;
        }

        int[] dist = new int[100000];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{n, n});
        dist[n] = n;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int cost = curr[0];
            int u = curr[1];

            if (u == m) {
                return cost;
            }

            if (cost > dist[u]) {
                continue;
            }

            char[] s = String.valueOf(u).toCharArray();
            int len = s.length;

            for (int i = 0; i < len; i++) {
                char orig = s[i];

                if (orig < '9') {
                    s[i] = (char) (orig + 1);
                    int nextNum = Integer.parseInt(new String(s));
                    if (!isPrime[nextNum] && cost + nextNum < dist[nextNum]) {
                        dist[nextNum] = cost + nextNum;
                        pq.offer(new int[]{dist[nextNum], nextNum});
                    }
                }

                if (orig > '0') {
                    if (i == 0 && orig == '1' && len > 1) {
                        s[i] = orig;
                        continue;
                    }
                    s[i] = (char) (orig - 1);
                    int nextNum = Integer.parseInt(new String(s));
                    if (!isPrime[nextNum] && cost + nextNum < dist[nextNum]) {
                        dist[nextNum] = cost + nextNum;
                        pq.offer(new int[]{dist[nextNum], nextNum});
                    }
                }

                s[i] = orig;
            }
        }

        return -1;
    }
}