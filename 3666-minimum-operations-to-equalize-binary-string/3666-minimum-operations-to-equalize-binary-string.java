import java.util.*;

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int initialZeros = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                initialZeros++;
            }
        }

        if (initialZeros == 0) {
            return 0;
        }

        TreeSet<Integer>[] unvisited = new TreeSet[2];
        unvisited[0] = new TreeSet<>();
        unvisited[1] = new TreeSet<>();

        for (int i = 0; i <= n; i++) {
            unvisited[i % 2].add(i);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{initialZeros, 0});
        unvisited[initialZeros % 2].remove(initialZeros);

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int z = curr[0];
            int steps = curr[1];

            int minI = Math.max(0, k - (n - z));
            int maxI = Math.min(k, z);

            int left = z + k - 2 * maxI;
            int right = z + k - 2 * minI;
            int parity = (z + k) % 2;

            TreeSet<Integer> set = unvisited[parity];
            Integer next = set.ceiling(left);

            while (next != null && next <= right) {
                if (next == 0) {
                    return steps + 1;
                }
                queue.offer(new int[]{next, steps + 1});
                set.remove(next);
                next = set.ceiling(left);
            }
        }

        return -1;
    }
}