class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        boolean[] isPrime = new boolean[maxVal + 1];
        Arrays.fill(isPrime, true);
        if (maxVal >= 0) isPrime[0] = false;
        if (maxVal >= 1) isPrime[1] = false;

        for (int p = 2; p * p <= maxVal; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= maxVal; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        Map<Integer, List<Integer>> primeToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            int temp = val;
            for (int p = 2; p * p <= temp; p++) {
                if (temp % p == 0) {
                    primeToIndices.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
                    while (temp % p == 0) {
                        temp /= p;
                    }
                }
            }
            if (temp > 1) {
                primeToIndices.computeIfAbsent(temp, k -> new ArrayList<>()).add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visitedIdx = new boolean[n];
        Set<Integer> visitedPrimes = new HashSet<>();

        queue.offer(0);
        visitedIdx[0] = true;
        int jumps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int k = 0; k < size; k++) {
                int curr = queue.poll();

                if (curr == n - 1) {
                    return jumps;
                }

                if (curr + 1 < n && !visitedIdx[curr + 1]) {
                    visitedIdx[curr + 1] = true;
                    queue.offer(curr + 1);
                }
                if (curr - 1 >= 0 && !visitedIdx[curr - 1]) {
                    visitedIdx[curr - 1] = true;
                    queue.offer(curr - 1);
                }

                int val = nums[curr];
                if (isPrime[val] && !visitedPrimes.contains(val)) {
                    visitedPrimes.add(val);
                    List<Integer> nextIndices = primeToIndices.get(val);
                    if (nextIndices != null) {
                        for (int nextIdx : nextIndices) {
                            if (!visitedIdx[nextIdx]) {
                                visitedIdx[nextIdx] = true;
                                queue.offer(nextIdx);
                            }
                        }
                    }
                }
            }

            jumps++;
        }

        return -1;
    }
}